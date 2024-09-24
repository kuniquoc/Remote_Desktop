package quochung.server.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quochung.server.model.Favorite;
import quochung.server.model.StudyMethod;
import quochung.server.model.User;
import quochung.server.repository.FavoriteRepository;
import quochung.server.repository.StudyMethodRepository;
import quochung.server.repository.UserRepository;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired

    private StudyMethodRepository studyMethodRepository;

    public void addFavorite(Long userId, Long studyMethodId) throws BadRequestException {
        Favorite favorite = new Favorite();
        User user = userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found"));
        favorite.setUser(user);
        StudyMethod studyMethod = studyMethodRepository.findById(studyMethodId)
                .orElseThrow(() -> new BadRequestException("Study method not found"));
        favorite.setStudyMethod(studyMethod);
        favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long userId, Long studyMethodId) throws BadRequestException {
        Favorite favorite = favoriteRepository.findByUserIdAndStudyMethodId(userId, studyMethodId)
                .orElseThrow(() -> new BadRequestException("Favorite not found"));
        favoriteRepository.delete(favorite);
    }

    public List<StudyMethod> getFavoritesByUserId(Long userId) {
        List<StudyMethod> studyMethods = favoriteRepository.findByUserId(userId).stream().map(favStudyMethod -> {
            return favStudyMethod.getStudyMethod();
        }).toList();
        return studyMethods;
    }
}
