package ru.kosterror.sportteamapi.service.teammember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.mapper.teammember.TeamMemberMapper;
import ru.kosterror.sportteamapi.model.TeamMember;
import ru.kosterror.sportteamapi.repository.TeamMemberRepository;

/**
 * Класс, реализующий {@link TeamMemberService}. Используется для взаимодействия с {@link TeamMember}.
 */
@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final TeamMemberMapper teamMemberMapper;

    @Override
    public TeamMemberDto getTeamMember(Long id) throws NotFoundException {
        TeamMember teamMember = teamMemberRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Участник с id = '" + id + "' не найден"));

        return teamMemberMapper.entityToDto(teamMember);
    }

}
