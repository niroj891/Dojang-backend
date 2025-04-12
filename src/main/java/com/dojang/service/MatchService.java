package com.dojang.service;

import com.dojang.dao.MatchDao;
import com.dojang.model.Participation;
import com.dojang.model.Result;
import com.dojang.model.User;
import com.dojang.model.WeightCategory;
import com.dojang.response.MatchResultResponse;
import com.dojang.response.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchDao matchRepository;

    public List<MatchResultResponse> getMatchResultsWithPlayers(Integer eventId, WeightCategory weightCategory) {
        return matchRepository.findByEventIdAndWeightCategoryOrderByRoundNumber(eventId,weightCategory).stream()
                .map(match -> {
                    MatchResultResponse response = new MatchResultResponse();
                    response.setMatchId(match.getId());
                    response.setWeightCategory(match.getWeightCategory());
                    response.setRoundNumber(match.getRoundNumber());
                    response.setMatchDate(match.getMatchDate());
                    response.setStatus(match.getStatus());

                    if (match.getResult() != null) {
                        Result result = match.getResult();
                        response.setResultId(result.getResultId());
                        response.setWinner(convertToPlayerDto(result.getWinner()));
                        response.setLoser(convertToPlayerDto(result.getLosser()));
                    }

                    return response;
                })
                .collect(Collectors.toList());
    }

    private PlayerDto convertToPlayerDto(Participation participation) {
        PlayerDto dto = new PlayerDto();
        dto.setId(participation.getId());
        dto.setFirstName(participation.getFirstName());
        dto.setLastName(participation.getLastName());
        dto.setDojangName(participation.getDojangName());
        dto.setWeightCategory(participation.getWeightCategory());

        User user = participation.getUser();
        if (user != null) {
            dto.setUserId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setUserFirstName(user.getFirstName());
            dto.setUserLastName(user.getLastName());
        }

        return dto;
    }
}