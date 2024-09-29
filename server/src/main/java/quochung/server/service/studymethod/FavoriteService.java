package quochung.server.service.studymethod;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quochung.server.model.studymethod.Favorite;
import quochung.server.model.studymethod.StudyMethod;
import quochung.server.model.user.User;
import quochung.server.repository.studymethod.FavoriteRepository;
import quochung.server.repository.studymethod.StudyMethodRepository;
import quochung.server.repository.user.UserRepository;

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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy người dùng"));
        favorite.setUser(user);
        StudyMethod studyMethod = studyMethodRepository.findById(studyMethodId)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phương pháp học"));
        favorite.setStudyMethod(studyMethod);
        favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long userId, Long studyMethodId) throws BadRequestException {
        Favorite favorite = favoriteRepository.findByUserIdAndStudyMethodId(userId, studyMethodId)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phương pháp học trong danh sách yêu thích"));
        favoriteRepository.delete(favorite);
    }

    public List<StudyMethod> getFavoritesByUserId(Long userId) {
        List<StudyMethod> studyMethods = favoriteRepository.findByUserId(userId).stream().map(favStudyMethod -> {
            return favStudyMethod.getStudyMethod();
        }).toList();
        return studyMethods;
    }
}
