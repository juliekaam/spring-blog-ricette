package org.test.blogricette.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.test.blogricette.model.Ricetta;
import org.test.blogricette.repository.RicettaRepository;

import java.util.List;


@Controller
@RequestMapping ("/ricette")
public class IndexController {
    @Autowired
    private RicettaRepository ricettaRepository;

    // metodo index che mostra la lista di tutte le ricette
    @GetMapping
    public String index(Model model) {

        List<Ricetta> ricettaList = ricettaRepository.findAll();// questa è la lista delle ricette presa da database
        model.addAttribute("receipt", ricettaList);// passo la lista delle ricette al model
        return "ricette/lista";
    }


    // controller che mostra la pagina di creazione di una ricetta
    @GetMapping("/create") // url
    public String create(Model model) {
        // aggiungiamo al model un attributo di tipo Book
        model.addAttribute("receipt", new Ricetta());

        return "ricette/form"; // template
    }

    //metodo che gestisce la POST di creazione di una ricetta


    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("receipt") Ricetta formRicetta,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ricette/form"; // template
        }


        formRicetta.setTitle(formRicetta.getTitle().toUpperCase());

        // per salvare la ricetta su database chiama in aiuto il ricettaRepository
        ricettaRepository.save(formRicetta);
        // se la ricetta è stata salvata con successo faccio una redirect alla pagina della lista
        return "redirect:/ricette";
    }
}

          /* /* metodi per update
            @GetMapping("/edit/{id}")
            public String edit(@PathVariable Integer id, org.springframework.ui.Model model) {
                // cerco su database il libro con quell'id
                Optional<Pizza> result = pizzaRepository.findById(id);
                // verifico se il book è presente
                if (result.isPresent()) {
                    // passo il Book al model come attributo
                    model.addAttribute("pizza", result.get());
                    // ritorno il template con il form di edit
                    return "pizze/edit";
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
                }
            }

            // postmapping che riceve il submit
            @PostMapping("/edit/{id}")
            public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza,
                                 BindingResult bindingResult) {
                // valido i dati
                if (bindingResult.hasErrors()) {
                    // si sono verificati degli errori di validazione
                    return "/pizze/edit"; // nome del template per ricreare la view
                }
                // salvo il Book
                pizzaRepository.save(formPizza);
                return "redirect:/pizze";
            }

            // metodo per la delete
            @PostMapping("/delete/{id}")
            public String deleteById(@PathVariable Integer id) {
                // cancello il book
                pizzaRepository.deleteById(id);
                // rimando alla pagina con la lista
                return "redirect:/pizze";
            }

        }




        return "redirect:/ricette";
    }
    */

