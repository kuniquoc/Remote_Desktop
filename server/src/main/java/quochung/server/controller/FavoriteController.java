package quochung.server.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import quochung.server.payload.MessageDto;
import quochung.server.service.FavoriteService;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestParam("userId") Long userId,
            @RequestParam("studyMethodId") Long studyMethodId) {
        try {
            favoriteService.addFavorite(userId, studyMethodId);
            return ResponseEntity.status(201).body(new MessageDto("Favorite added successfully"));
        } catch (BadRequestException e) {
            return ResponseEntity.status(404).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal server error"));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> removeFavorite(@RequestParam("userId") Long userId,
            @RequestParam("studyMethodId") Long studyMethodId) {
        try {
            favoriteService.removeFavorite(userId, studyMethodId);
            return ResponseEntity.noContent().build();
        } catch (BadRequestException e) {
            return ResponseEntity.status(404).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal server error"));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getFavoritesByUserId(@RequestParam("userId") Long userId) {
        try {
            String message = "User's favorite study methods retrieved successfully";
            Object data = favoriteService.getFavoritesByUserId(userId);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal server error"));
        }
    }
}
