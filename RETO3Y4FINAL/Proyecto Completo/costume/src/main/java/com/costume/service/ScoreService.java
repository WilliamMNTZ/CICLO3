package com.costume.service;

import com.costume.model.Score;
import com.costume.repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id) {
        return scoreRepository.getScore(id);
    }

    public Score save(Score score) {
        if (score.getIdScore() == null) {
            return scoreRepository.save(score);
        } else {
            Optional<Score> scoreUno = scoreRepository.getScore(score.getIdScore());
            if (scoreUno.isEmpty()) {
                return scoreRepository.save(score);
            } else {
                return score;
            }
        }
    }

    public boolean deleteScore(int id) {
        Optional<Score> puntaje = scoreRepository.getScore(id);

        if (puntaje.isEmpty()) {
            return false;
        } else {
            scoreRepository.delete(puntaje.get());
            return true;
        }
    }

    public Score update(Score score) {
        if (score.getIdScore() != null) {
            Optional<Score> e = scoreRepository.getScore(score.getIdScore());
            if (!e.isEmpty()) {
                if (score.getMessageText() != null) {
                    e.get().setMessageText(score.getMessageText());
                }
                if (score.getStars() != null && score.getStars() >= 0 && score.getStars() <= 5) {
                    e.get().setStars(score.getStars());
                }
                scoreRepository.save(e.get());
                return e.get();
            } else {
                return score;
            }
        } else {
            return score;
        }
    }
}
