package com.dojang.service;

import com.dojang.dao.MatchDao;
import com.dojang.dao.ParticipationDao;
import com.dojang.model.Match;
import com.dojang.model.Participation;
import com.dojang.model.WeightCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchResultService {
    private final MatchDao matchRepository;
    private final ParticipationDao participationRepository;

    public List<ParticipationResultDto> getRecentWinners(Integer eventId, WeightCategory weightCategory) {
        // Get matches ordered by most recent first
        List<Match> matches = matchRepository.findByEventAndWeightCategoryOrderByDateDesc(
                eventId, weightCategory);

        if (matches.isEmpty()) {
            return Collections.emptyList();
        }

        List<ParticipationResultDto> results = new ArrayList<>();

        // First Winner - Winner of last match
        Match lastMatch = matches.get(0);
        if (lastMatch.getResult() != null && lastMatch.getResult().getWinner() != null) {
            results.add(new ParticipationResultDto(
                    lastMatch.getResult().getWinner(),
                    "FIRST_WINNER (Last Match Winner)"
            ));
        }

        // Second Winner - Loser of last match
        if (lastMatch.getResult() != null && lastMatch.getResult().getLosser() != null) {
            results.add(new ParticipationResultDto(
                    lastMatch.getResult().getLosser(),
                    "SECOND_WINNER (Last Match Loser)"
            ));
        }

        // Third Winner - Loser of second last match (if exists)
        if (matches.size() > 1 && matches.get(1).getResult() != null
                && matches.get(1).getResult().getLosser() != null) {
            results.add(new ParticipationResultDto(
                    matches.get(1).getResult().getLosser(),
                    "THIRD_WINNER (Second Last Match Loser)"
            ));
        }

        return results;
    }

    // DTO to hold the result
    @Getter
    @AllArgsConstructor
    public static class ParticipationResultDto {
        private Participation participation;
        private String position;
    }
}
