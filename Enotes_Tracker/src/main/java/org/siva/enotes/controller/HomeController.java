package org.siva.enotes.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.siva.enotes.model.Notes;
import org.siva.enotes.model.Todo;
import org.siva.enotes.model.User;
import org.siva.enotes.service.INotesService;
import org.siva.enotes.service.ITodoService;
import org.siva.enotes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private INotesService notesService;
	
	@Autowired
	private ITodoService todoService;
	
	@GetMapping("/")
	public String indexPage() {
		return "Index";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "Register";
	}
	
	@PostMapping("/saveUserInformation")
	public String saveUserInformation(@ModelAttribute User user, HttpSession session) {
		System.out.println(user);
		boolean emailIsPresent = userService.emailIsPresent(user.getEmail());
		boolean mobileIsPresent = userService.mobileIsPresent(user.getMobile());
		if (emailIsPresent && mobileIsPresent) {
			session.setAttribute("failMsg", "email id and mobile number already exists");
		}
		else if (emailIsPresent) {
			session.setAttribute("failMsg", "email id already exists");
		}
		else if (mobileIsPresent) {
			session.setAttribute("failMsg", "mobile number is already exists");
		}
		else {
			User save = userService.addUser(user);
			if (save!=null) {
				session.setAttribute("successMsg", "congrats, registration successfully completed");
			}
			else {
				session.setAttribute("successMsg", "something went wrong on the server");
			}
		}
		return "redirect:/register";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "Login";
	}
	
	@PostMapping("/loginInformation")
	public String loginInformation(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		User login = userService.login(email, password);
		if (login!=null) {
			session.setAttribute("user", login);
			return "redirect:/user/home";
		}
		else {
			session.setAttribute("failMsg", "Invalid Credentials");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/home")
	public String homePage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			return "Home";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/addnotes") 
	public String addNotes(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			return "AddNotes";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/user/savenotes")
	public String saveNotes(@RequestParam("title") String title,@RequestParam("description") String description,  HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			Notes notes = new Notes();
			notes.setTitle(title);
			notes.setDescription(description);
			notes.setDate(Date.valueOf(LocalDate.now()));
			notes.setUser(user);
			Notes saveNotes = notesService.saveNotes(notes);
			if (saveNotes!=null) {
				session.setAttribute("successMsg", "notes added successfully");
				return "redirect:/user/addnotes";
			}
			else {
				session.setAttribute("failMsg", "something went wrong on the server");
				return "redirect:/user/addnotes";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/viewnotes")
	public String vieewNotes(HttpSession session, Model model, @RequestParam(defaultValue = "0") int pageNo) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			Page<Notes> notes = notesService.findAllNotes(user, pageNo);
			if (notes!=null) {
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalElements", notes.getTotalElements());
				model.addAttribute("totalPages", notes.getTotalPages()); 
				model.addAttribute("notesList", notes.getContent());
				return "ViewNotes";
			}
			else {
				return "redirect:/user/home";
			}
		}
		return "redirect:/login";
	}
	
	@GetMapping("/user/readnotes/{id}")
	public String readNotes(@PathVariable("id") int id, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			Notes notes = notesService.findByNotes(id);
			if (notes!=null) {
				model.addAttribute("entry", notes);
				return "Readnotes";
			}
			else {
				session.setAttribute("failMsg", "id not exist");
				return "redirect:/user/viewnotes";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/updatenotes/{id}")
	public String updatenotes(@PathVariable("id") int id, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			Notes notes = notesService.findByNotes(id);
			if (notes!=null) {
				model.addAttribute("entry", notes);
				return "UpdateNotes";
			}
			else {
				session.setAttribute("failMsg", "id not exist");
				return "redirect:/user/viewnotes";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/user/updatedNotes")
	public String updatednotes(HttpSession session, @ModelAttribute Notes notes) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			notes.setDate(Date.valueOf(LocalDate.now()));
			notes.setUser(user);
			Notes updateNotes = notesService.saveNotes(notes);
			if (updateNotes!=null) {
				session.setAttribute("successMsg", "notes updated successfully");
				return "redirect:/user/viewnotes";
			}
			else {
				session.setAttribute("failMsg", "something went wrong on the server");
				return "redirect:/user/viewnotes";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/removenotes/{id}")
	public String removenotes(@PathVariable("id") int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			notesService.removeNotes(id);
			session.setAttribute("successMsg", "notes removed successfully");
			return "redirect:/user/viewnotes";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/addtodo")
	public String addTodo(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			return "AddTodo";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/user/savetodo")
	public String saveTodo(Model model, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("status") String status, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			Todo todo = new Todo();
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setStatus(status);
			todo.setDate(Date.valueOf(LocalDate.now()));
			todo.setUser(user);
			Todo save = todoService.addTodo(todo);
			if (save!=null) {
				session.setAttribute("successMsg", "todo added successfully");
				return "redirect:/user/addtodo";
			}
			else {
				session.setAttribute("failMsg", "something went wrong on the server");
				return "redirect:/user/addtodo";
			}
			
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/viewtodos")
	public String viewTodo(HttpSession session, Model model, @RequestParam(defaultValue = "0") int pageNo) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			Page<Todo> pages = todoService.getAllTodosByUser(user, pageNo);
			if (pages!=null) {
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalElements", pages.getTotalElements());
				model.addAttribute("totalPages", pages.getTotalPages());
				model.addAttribute("todos", pages.getContent());
				return "ViewTodos";
			}
			else {
				return "redirect:/user/home";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/updatetodo/{id}")
	public String updateTodo(HttpSession session, Model model, @PathVariable("id") int id) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			Todo todo = todoService.getTodoById(id);
			if (todo!=null) {
				model.addAttribute("todo", todo);
				return "UpdateTodo";
			}
			else {
				session.setAttribute("failMsg", "something went wrong on server please check the todo details");
				return "redirect:/user/viewtodos";
			}
		} 
		else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/user/updateTodoInformation")
	public String updateTodoInformation(HttpSession session, @ModelAttribute Todo todo) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			todo.setDate(Date.valueOf(LocalDate.now()));
			todo.setUser(user);
			Todo update = todoService.addTodo(todo);
			if (update!=null) {
				session.setAttribute("successMsg", "todo updated successfully");
				return "redirect:/user/viewtodos";
			} 
			else {
				session.setAttribute("failMsg", "something went wrong on the server");
				return "redirect:/user/viewtodos";
			}
		} 
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/readtodo/{id}")
	public String readTodo(HttpSession session, @PathVariable("id") int id, Model model) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			Todo todo = todoService.getTodoById(id);
			if (todo!=null) {
				model.addAttribute("todo", todo);
				return "ReadTodo";
			}
			else {
				return "redirect:/user/viewtodos";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/removetodo/{id}")
	public String removeTodo(HttpSession session, @PathVariable("id") int id) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			todoService.removeTodoById(id);
			session.setAttribute("successMsg", "todo removed successfully");
			return "redirect:/user/viewtodos";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/viewprofile")
	public String viewProfile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("user", user);
			return "ViewProfile";
		}
		else {
			return "redirect:/login";  
		} 
	}
	
	@GetMapping("/user/editprofile/{id}")
	public String editProfile(HttpSession session, @PathVariable("id") int id, Model model) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("user", user);
			return "UpdateProfile";
		}
		else {
			session.setAttribute("failMsg", "something went wrong on the server");
			return "redirect:/user/viewprofile";
		}
	}
	
	@PostMapping("/user/updateInformation")
	public String updateInformation(HttpSession session, @ModelAttribute User user, Model model) {
		User update = userService.addUser(user);
		if (update!=null) {
			session.setAttribute("user", update);
			session.setAttribute("successMsg", "user information updated successfully");
			return "redirect:/user/viewprofile";
		}
		else {
			session.setAttribute("failMsg", "something went wrong on the server");
			return "redirect:/user/viewprofile";
		}
	}
	
	@GetMapping("/user/deleteprofile/{id}")
	public String deleteAccount(HttpSession session, @PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user!=null) {
			notesService.removeNotesByUser(user);
			todoService.removeTodosByUser(user);
			boolean deleteUser = userService.deleteUser(user);
			if (deleteUser) {
				session.setAttribute("successMsg", "your account permunantly deleted");
				return "redirect:/login";
			}
			else {
				session.setAttribute("failMsg", "something went wrong on the server");
				return "redirect:/user/viewprofile";
			}
		} else {
			session.setAttribute("failMsg", "userid not found");
			return "redirect:/user/viewprofile";
		}
	}
	
	@GetMapping("/forgotpassword")
	public String forgotPassword() {
		return "ForgotPassword";
	}
	
	@PostMapping("/next")
	public String emailAndMobileNumberChecking(HttpSession session, @RequestParam("email") String email, @RequestParam("mobile") String mobile, Model model) {
		User user = userService.getByEmailAndMobile(email, mobile);
		if (user!=null) {
			model.addAttribute("email", email);
			return "ForgotPassword1";
		}
		else {
			session.setAttribute("failMsg", "email and mobile number is incorrect");
			return "redirect:/forgotpassword";
		}
	}
	
	@PostMapping("/changepassword")
	public String emailAndMobileNumber(HttpSession session,@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("cpassword") String cpassword) {
		if (password.equals(cpassword)) {
			User user = userService.getUserByEmail(email);
			if (user!=null) {
				user.setPassword(password);
				User updatepassword = userService.addUser(user);
				if (updatepassword!=null) {
					session.setAttribute("successMsg", "user password successfully updated");
					return "redirect:/login"; 
				}
				else {
					session.setAttribute("failMsg", "something went wrong on the server");
					return "redirect:/login";
				}
			}
			else {
				session.setAttribute("successMsg", "something went wrong on the server");
				return "redirect:/login";  
			}
		}
		else {
			session.setAttribute("failMsg", "new password and confirm password most be same");
			return "ForgotPassword1";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.setAttribute("logout", "logout successfully completed");
		return "redirect:/login";
	}
	
	
	
}
