package ua.itea.FinalProject.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.itea.FinalProject.Entity.Customer;
import ua.itea.FinalProject.Entity.Item;
import ua.itea.FinalProject.Entity.ItemDetails;
import ua.itea.FinalProject.Repository.CustomerRepository;
import ua.itea.FinalProject.Repository.ItemDetailsRepository;
import ua.itea.FinalProject.Repository.ItemRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class StoController {
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final ItemDetailsRepository itemDetailsRepository;

    @Autowired
    public StoController(ItemRepository itemRepository, CustomerRepository customerRepository, ItemDetailsRepository itemDetailsRepository) {
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.itemDetailsRepository = itemDetailsRepository;
    }

    @GetMapping("/")
    public String showListOrders(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "list_orders";
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable int id, Model model) {
        Optional<Item> optionalOrder = itemRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Item item = optionalOrder.get();
            model.addAttribute("item", item);
            return "view_order";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/customer/{fullName}")
    public String showCustomerByName(@PathVariable String fullName, Model model) {
        Optional<Customer> optionalCustomer = customerRepository.findByName(fullName);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            model.addAttribute("customer", customer);
            return "view_customer";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/create_order")
    public String createOrderForm(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "create_order";
    }

    @PostMapping("/create_order")
    public String createOrder(@RequestParam int customerId, @RequestParam String status, @RequestParam String additionalInfo) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            ItemDetails details = new ItemDetails();
            details.setStatus(status);
            details.setAdditionalInfo(additionalInfo);
            details.setDate(new Date());
            itemDetailsRepository.save(details);
            Item item = new Item(customer, details);
            itemRepository.save(item);
        }
        return "redirect:/";
    }

    @GetMapping("/edit_order/{id}")
    public String editOrderForm(@PathVariable int id, Model model) {
        Optional<Item> optionalOrder = itemRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Item item = optionalOrder.get();
            model.addAttribute("item", item);
            return "edit_order";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/update_order/{id}")
    public String updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        Optional<Item> optionalOrder = itemRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Item item = optionalOrder.get();
            item.getDetails().setStatus(status);
            item.getDetails().setDate(new Date());
            itemRepository.save(item);
        }
        return "redirect:/";
    }


    @GetMapping("/create_customer")
    public String createCustomerForm() {
        return "create_customer";
    }

    @PostMapping("/create_customer")
    public String createCustomer(@RequestParam String fullName, @RequestParam String phone) {
        Customer customer = new Customer(fullName, phone);
        customerRepository.save(customer);
        return "redirect:/";
    }

    @GetMapping("/delete_order/{id}")
    public String showDeleteOrderPage(@PathVariable int id, Model model) {
        Optional<Item> optionalOrder = itemRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Item item = optionalOrder.get();
            model.addAttribute("item", item);
        }
        return "delete_order";
    }

    @PostMapping("/delete_order/{id}")
    public String deleteOrder(@PathVariable int id) {
        Optional<Item> optionalOrder = itemRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Item item = optionalOrder.get();
            Customer customer = item.getCustomer();
            customer.getItems().remove(item);
            itemRepository.deleteById(id);
            customerRepository.save(customer);
        }
        return "redirect:/";
    }


}
