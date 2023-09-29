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
import java.util.Optional;


@Controller
@RequestMapping ("/ricette")
public class IndexController {
    @Autowired
    private RicettaRepository ricettaRepository;

    // metodo index che mostra la lista di tutte le ricette
    @GetMapping
    public String index(Model model) {

        List<Ricetta> ricettaList = ricettaRepository.findAll();// questa è la lista delle ricette presa da database
        model.addAttribute("recipe", ricettaList);// passo la lista delle ricette al model
        return "ricette/home";
    }


    // controller che mostra la pagina di creazione di una ricetta
    @GetMapping("/create") // url
    public String create(Model model) {
        // aggiungiamo al model un attributo di tipo Book
        model.addAttribute("recipe", new Ricetta());

        return "ricette/form"; // template
    }

    //metodo che gestisce la POST di creazione di una ricetta


    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("recipe") Ricetta formRicetta,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ricette/form"; // template
        }




        // per salvare la ricetta su database chiama in aiuto il ricettaRepository
        ricettaRepository.save(formRicetta);
        // se la ricetta è stata salvata con successo faccio una redirect alla pagina della lista
        return "redirect:/ricette";
    }


    // metodi per update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // cerco su database la ricetta con quell'id

        Optional<Ricetta> result = ricettaRepository.findById(id);
        // verifico se la ricetta è presente
        if (result.isPresent()) {
            // passo la ricetta al model come attributo
            model.addAttribute("Ricetta", result.get());
            // ritorno il template con il form di edit
            return "ricette/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "receipt with id " + id + " not found");
        }
    }

    // postmapping che riceve il submit
    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("Ricetta") Ricetta formRicetta,
                         BindingResult bindingResult) {
        // valido i dati
        if (bindingResult.hasErrors()) {
            // si sono verificati degli errori di validazione
            return "/ricette/edit"; // nome del template per ricreare la view
        }
        // salvo la ricetta
        ricettaRepository.save(formRicetta);

        return "redirect:/ricette";
    }
    @GetMapping("/show")
    public String show(Model model){

      return "ricette/detail"  ;
    }

}

            /*// metodo per la delete
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

