package com.codeup.blogpractice.controllers;

import com.codeup.blogpractice.models.Ad;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class AdsController {
    @GetMapping("/ads")
//    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String index(Model model){
        List<Ad> adsList = adsDao.findAll();
        model.addAttribute("noAdsFound", adsList.size() == 0);
        model.addAttribute("ads", adsList);
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model model){
        Ad ad = adsDao.getOne(id);
        model.addAttribute("adId", id);
        model.addAttribute("ad", ad);
        return "/ads/show";
    }

    @GetMapping("/ads/create")
    public String showForm(Model viewModel){
        viewModel.addAttribute("ad", new Ad());
        return "/ads/create";
    }


    @PostMapping("/ads/create")
    public String save(@ModelAttribute Ad adToBeSaved) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        adToBeSaved.setOwner(currentUser);
        Ad savedAd = adsDao.save(adToBeSaved);
        emailService.prepareAndSend(savedAd, "A new ad has been created", "An ad has been created with the id of " + savedAd.getId());
        return "redirect:/ads/" + savedAd.getId();
    }

}
