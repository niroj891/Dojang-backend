package com.dojang.service;

import com.dojang.dao.EventDao;
import com.dojang.dao.ParticipationDao;
import com.dojang.dto.EventParticipantsByWeightDTO;
import com.dojang.model.Event;
import com.dojang.model.WeightCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private EventDao eventRepository;

    @Autowired
    private ParticipationDao participationRepository;

    public List<EventParticipantsByWeightDTO> getParticipantsByWeightForInstructorEvents(Integer instructorId) {
        // Get all events created by the instructor
        List<Event> instructorEvents = eventRepository.findByInstructorId(instructorId);

        return instructorEvents.stream()
                .map(event -> {
                    // Get participant counts by weight category for this event
                    List<Object[]> results = participationRepository.countParticipantsByWeightCategoryForEvent(event.getEventId());

                    // Convert the results to a map
                    Map<WeightCategory, Long> weightCountMap = new HashMap<>();
                    for (Object[] result : results) {
                        weightCountMap.put((WeightCategory) result[0], (Long) result[1]);
                    }

                    // Ensure all weight categories are represented (even with zero counts)
                    for (WeightCategory category : WeightCategory.values()) {
                        weightCountMap.putIfAbsent(category, 0L);
                    }

                    return new EventParticipantsByWeightDTO(
                            event.getEventId(),
                            event.getTitle(),
                            weightCountMap
                    );
                })
                .collect(Collectors.toList());
    }
}