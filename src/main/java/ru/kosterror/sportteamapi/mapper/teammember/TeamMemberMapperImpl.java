package ru.kosterror.sportteamapi.mapper.teammember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.dto.teammember.BasicTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.CreateUpdateTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.TeamMemberRoleDto;
import ru.kosterror.sportteamapi.mapper.sportteam.SportTeamMapper;
import ru.kosterror.sportteamapi.mapper.teammemberrole.TeamMemberRoleMapper;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.model.TeamMember;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

/**
 * Класс, реализующий {@link TeamMemberMapper}. Используется для конвертации {@link TeamMember}
 * в связанные с ним классы и получения его из них.
 */
@Component
@RequiredArgsConstructor
public class TeamMemberMapperImpl implements TeamMemberMapper {

    private final SportTeamMapper sportTeamMapper;
    private final TeamMemberRoleMapper teamMemberRoleMapper;

    @Override
    public TeamMemberDto entityToDto(TeamMember teamMember) {
        SportTeam sportTeam = teamMember.getSportTeam();
        SportTeamDto sportTeamDto = sportTeam != null ? sportTeamMapper.entityToDto(sportTeam) : null;

        BasicTeamMemberDto basicTeamMemberDto = entityToBasicDto(teamMember);

        return TeamMemberDto
                .builder()
                .teamMember(basicTeamMemberDto)
                .team(sportTeamDto)
                .build();

    }

    @Override
    public BasicTeamMemberDto entityToBasicDto(TeamMember teamMember) {
        TeamMemberRole role = teamMember.getRole();
        TeamMemberRoleDto roleDto = role != null ? teamMemberRoleMapper.entityToDto(role) : null;

        return BasicTeamMemberDto
                .builder()
                .id(teamMember.getId())
                .name(teamMember.getName())
                .surname(teamMember.getSurname())
                .patronymic(teamMember.getPatronymic())
                .birthDate(teamMember.getBirthDate())
                .role(roleDto)
                .build();
    }

    @Override
    public TeamMember createDtoToEntity(CreateUpdateTeamMemberDto createUpdateTeamMemberDto,
                                        TeamMemberRole memberRole,
                                        SportTeam sportTeam
    ) {
        return TeamMember
                .builder()
                .sportTeam(sportTeam)
                .name(createUpdateTeamMemberDto.getName())
                .surname(createUpdateTeamMemberDto.getSurname())
                .patronymic(createUpdateTeamMemberDto.getPatronymic())
                .birthDate(createUpdateTeamMemberDto.getBirthDate())
                .role(memberRole)
                .build();
    }

}

