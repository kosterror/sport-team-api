package ru.kosterror.sportteamapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kosterror.sportteamapi.dto.ApiError;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.service.teammember.TeamMemberService;

@RestController
@RequestMapping("/api/team-members")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService service;

    /**
     * Метод для получения участника команды по идентификатору.
     *
     * @param id идентификатор участника.
     * @return информацией об участнике команды.
     * @throws NotFoundException возникает, если участник команды не найден.
     */
    @Operation(summary = "Получить участника команды по идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены."),
            @ApiResponse(responseCode = "404", description = "Участник с таким идентификатором не найден",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/{id}")
    public TeamMemberDto getTeamMember(@PathVariable Long id) throws NotFoundException {
        return service.getTeamMember(id);
    }

}
