package app.controller;

import app.entity.Contact;
import app.service.impl.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/contacts")
    public String fetchAllContacts(Model model) {
        List<Contact> list = contactService.fetchAll();
        model.addAttribute("title", "Contacts");
        model.addAttribute("contacts", list);
        model.addAttribute("fragmentName", "contact_list");
        return "layout";
    }

    @GetMapping("/create-contact")
    public String createContact(Model model) {
        model.addAttribute("title", "Add Contact");
        model.addAttribute("fragmentName", "contact_add");
        return "layout";
    }

    @PostMapping("/add-contact")
    public RedirectView addContact(@ModelAttribute Contact contact) {
        contactService.create(contact);
        return new RedirectView("/contacts");
    }

    @GetMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Contact");
        Contact contact = contactService.fetchById(id);
        model.addAttribute("contact", contact);
        model.addAttribute("fragmentName", "contact_update");
        return "layout";
    }

    @PostMapping("/change-contact")
    public RedirectView changeContact(@ModelAttribute Contact contact) {
        contactService.update(contact.getId(), contact);
        return new RedirectView("/contacts");
    }

    @GetMapping("/delete-contact/{id}")
    public RedirectView deleteContact(@PathVariable("id") Long id) {
        contactService.delete(id);
        return new RedirectView("/contacts");
    }
}

