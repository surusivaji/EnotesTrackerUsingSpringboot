package org.siva.enotes.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.siva.enotes.model.Notes;
import org.siva.enotes.model.User;
import org.siva.enotes.service.INotesService;
import org.siva.enotes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
			session.setAttribute("msg", "email id and mobile number already exists");
		}
		else if (emailIsPresent) {
			session.setAttribute("msg", "email id already exists");
		}
		else if (mobileIsPresent) {
			session.setAttribute("msg", "mobile number is already exists");
		}
		else {
			User save = userService.addUser(user);
			if (save!=null) {
				session.setAttribute("msg", "congrats, registration successfully completed");
			}
			else {
				session.setAttribute("msg", "something went wrong on the server");
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
			session.setAttribute("msg", "invalid credientials");
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
				session.setAttribute("msg", "notes added successfully");
				return "redirect:/user/addnotes";
			}
			else {
				session.setAttribute("msg", "something went wrong on the server");
				return "redirect:/user/addnotes";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/user/viewnotes")
	public String vieewNotes(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("username", user.getFullname());
			List<Notes> allNotes = notesService.findAllNotes(user);
			model.addAttribute("notes", allNotes);
			return "ViewNotes";
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
				session.setAttribute("msg", "id not exist");
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
				session.setAttribute("msg", "id not exist");
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
				session.setAttribute("msg", "notes updated successfully");
				return "redirect:/user/viewnotes";
			}
			else {
				session.setAttribute("msg", "something went wrong on the server");
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
			session.setAttribute("msg", "notes removed successfully");
			return "redirect:/user/viewnotes";
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
			session.setAttribute("msg", "something went wrong on the server");
			return "redirect:/user/viewprofile";
		}
	}
	
	@PostMapping("/user/updateInformation")
	public String updateInformation(HttpSession session, @ModelAttribute User user, Model model) {
		User update = userService.addUser(user);
		if (update!=null) {
			session.setAttribute("user", update);
			session.setAttribute("msg", "user information updated successfully");
			return "redirect:/user/viewprofile";
		}
		else {
			session.setAttribute("msg", "something went wrong on the server");
			return "redirect:/user/viewprofile";
		}
	}
	
	@GetMapping("/user/deleteprofile/{id}")
	public String deleteAccount(HttpSession session, @PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user!=null) {
			notesService.removeNotesByUser(user);
			boolean deleteUser = userService.deleteUser(user);
			if (deleteUser) {
				session.setAttribute("logout", "your account permunantly deleted");
				return "redirect:/login";
			}
			else {
				session.setAttribute("msg", "something went wrong on the server");
				return "redirect:/user/viewprofile";
			}
		} else {
			session.setAttribute("msg", "userid not found");
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
			session.setAttribute("msg", "email and mobile number is incorrect");
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
					session.setAttribute("msg", "user password successfully updated");
					return "redirect:/login"; 
				}
				else {
					session.setAttribute("msg", "something went wrong on the server");
					return "redirect:/login";
				}
			}
			else {
				session.setAttribute("msg", "something went wrong on the server");
				return "redirect:/login";  
			}
		}
		else {
			session.setAttribute("msg", "new password and confirm password most be same");
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
