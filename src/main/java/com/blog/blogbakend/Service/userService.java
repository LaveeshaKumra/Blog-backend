package com.blog.blogbakend.Service;


import com.blog.blogbakend.modals.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.blogbakend.Repository.userRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class userService {
    @Autowired
    private userRepository userRepository;
    public Optional<Users> CurrentUser(Principal prinicipal) {

        String username = prinicipal.getName();

        return userRepository.findByUsername(username);
    }
    public int getUserId(Principal principal)
    {
        String username = principal.getName();
        int  id = userRepository.findByUsername(username).get().getUserid();
        return id;
    }




    public List<Users> searchResult(String keyword) {
        List<Users> itemsList = userRepository.findAll();
        List<Users> foundList = new ArrayList<>();

        for (Users items : itemsList) {
            if (items.getUsername() != null && items.getDescription() != null && (items.getUsername().
                    toLowerCase().contains(keyword.toLowerCase())
                    || items.getDescription().toLowerCase().contains(keyword.toLowerCase())
                    || items.getFirstname().toLowerCase().contains(keyword.toLowerCase())
                    || items.getLastname().toLowerCase().contains(keyword.toLowerCase())
            )
            ) {
                foundList.add(items);
            }
        }
        return foundList;
    }

}




