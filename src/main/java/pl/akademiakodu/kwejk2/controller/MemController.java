package pl.akademiakodu.kwejk2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.kwejk2.model.Mem;
import pl.akademiakodu.kwejk2.model.MemComment;
import pl.akademiakodu.kwejk2.repository.CategoryRepository;
import pl.akademiakodu.kwejk2.repository.MemCommentRepository;
import pl.akademiakodu.kwejk2.repository.MemRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class MemController {

    @Autowired
    private MemRepository memRepository;
    @Autowired
    private MemCommentRepository memComment;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/mems/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("mem", new Mem());
        return "/mems/add";
    }
    @ResponseBody
    @PostMapping("/mems")
    public String create(@ModelAttribute @Valid Mem mem, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "You have incorrectly filled out the form";
        }
        memRepository.save(mem);
        return "created";
    }
/** witout validation
    @ResponseBody
    @PostMapping("/mems")
    public String create(@ModelAttribute Mem mem) {
        memRepository.save(mem);
        return "created";
    }
*/
    @GetMapping("/mems")
    public String listMems(ModelMap modelMap) {
        modelMap.addAttribute("mems", memRepository.findAll());
        return "/mems/index";
    }

    @GetMapping("mems/{memId}")
    public String mem(@PathVariable Long memId, ModelMap modelMap) {

        Optional<Mem> memOptional = memRepository.findById(memId);
        memOptional.ifPresent(mem -> {
            modelMap.addAttribute("mem", mem);
            modelMap.addAttribute("comments", mem.getComments());
        });

        return "mems/show";
    }
    /**
     @GetMapping("mems/{memId}")
     public String mem(@PathVariable Long memId, ModelMap modelMap){
     Optional<Mem> memOptional =  memRepository.findById(memId);
     memOptional.ifPresent(mem -> {
     modelMap.addAttribute("mem",mem);

     });

     //return "mems/show"; - 1 próba nie wyświetla komentarzy

     // modelMap.addAttribute( "comments",memComment.findAllById(memId).orElse(new MemComment("Brak komenrarzy"))); 2 próba - wyświetla tylko jeden komentarz

     // tu poniżej łapie komentarze wszystkie ale i tak ich nie wyswietla -3 próba
     //List<MemComment> array = new ArrayList<>();
     //List<Long> elements = new ArrayList<>();
     //elements.add(memId);
     //memComment.findAllById(elements).forEach(array::add);
     //modelMap.addAttribute("comments",array); - 3 próba

     return"/mems/show";
     }

     */
    @PostMapping("/mems/addComment")
    public String addComment(@RequestParam String commentBody,
                             @RequestParam Long memId) {
        Optional<Mem> memOptional = memRepository.findById(memId);
        memOptional.ifPresent(
                mem -> {
                    MemComment comment = new MemComment();
                    comment.setComment(commentBody);
                    mem.addComment(comment);
                    memRepository.save(mem);
                }
        );
        return "redirect:/mems/" + memId;
    }
}
