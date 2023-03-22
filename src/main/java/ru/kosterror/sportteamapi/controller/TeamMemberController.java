package ru.kosterror.sportteamapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kosterror.sportteamapi.dto.ApiError;
import ru.kosterror.sportteamapi.dto.teammember.BasicTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.CreateUpdateTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.MoveMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.service.teammember.TeamMemberService;

import java.util.List;

@RestController
@RequestMapping("/api/team-members")
@RequiredArgsConstructor
@Tag(name = "Работа с участниками спортивных команд.")
public class TeamMemberController {

    private final TeamMemberService service;

    /**
     * Метод для получения участников с определенными параметрами.
     *
     * @param sportTeamIds      список идентификаторов доступных команд.
     * @param teamMemberRoleIds список идентификаторов доступных ролей.
     * @return список подходящий участников.
     */
    @Operation(summary = "Получить всех участников.", description = "Параметры необязательны. Если перечислены " +
            "идентификаторы команд, то в результате будет список с участниками, которые относятся только к этим " +
            "командам. Если указаны идентификаторы ролей, то список участников сможет содержать только тех " +
            "участников, которые имеют одну из заданных ролей. Параметры могут дополнять друг друга.")
    @ApiResponse(responseCode = "200", description = "Данные успешно получены.")
    @GetMapping
    public List<TeamMemberDto> getTeamMembers(@RequestParam(required = false) List<Long> sportTeamIds,
                                              @RequestParam(required = false) List<Long> teamMemberRoleIds) {
        return service.getTeamMembers(sportTeamIds, teamMemberRoleIds);
    }

    /**
     * Метод для получения участников команд, которые относятся к конкретной спортивной команде. Есть возможность
     * отфильтровать участников по роли.
     *
     * @param sportTeamId      идентификатор спортивной команды. Не может быть {@code null}.
     * @param teamMemberRoleId идентификатор роли, может быть {@code null}.
     * @return список подходящих участников.
     * @throws NotFoundException возникает, если спортивной команды или роли не существует.
     */
    @Operation(summary = "Получить всех участников конкретной команды.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены."),
            @ApiResponse(responseCode = "404", description = "Не удалось найти команду и/или роль " +
                    "участника по заданным значениям",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/sport-teams/{id}")
    public List<BasicTeamMemberDto> getTeamMembersByTeam(@PathVariable(name = "id") Long sportTeamId,
                                                         @RequestParam(required = false) Long teamMemberRoleId
    ) throws NotFoundException {
        return service.getTeamMembersByTeam(sportTeamId, teamMemberRoleId);
    }

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
            @ApiResponse(responseCode = "404", description = "Участник с таким идентификатором не найден.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/{id}")
    public TeamMemberDto getTeamMember(@PathVariable Long id) throws NotFoundException {
        return service.getTeamMember(id);
    }

    /**
     * Метод для создания участника команды.
     *
     * @param createUpdateTeamMemberDto информация для создания участника.
     * @return сохраненной информация о созданном участнике команды.
     * @throws NotFoundException возникает, если по идентификатору команды и/или роли не удалось найти информации.
     */
    @Operation(summary = "Создать участника.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Участник успешно создан."),
            @ApiResponse(responseCode = "404", description = "Не удалось найти команду и/или роль " +
                    "по заданным значениям для создания участника.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping
    public TeamMemberDto createTeamMember(
            @RequestBody CreateUpdateTeamMemberDto createUpdateTeamMemberDto
    ) throws NotFoundException {
        return service.createTeamMember(createUpdateTeamMemberDto);
    }

    /**
     * Метод для изменения данных участника команды.
     *
     * @param createUpdateTeamMemberDto новая информация об участнике.
     * @return новая сохраненная информация.
     * @throws NotFoundException возникает, если какая-либо информация по указанным идентификаторам не найдена.
     */
    @Operation(summary = "Изменить участника.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Участник успешно изменен."),
            @ApiResponse(responseCode = "404", description = "Не удалось найти участника и/или команду и/или роль " +
                    "по заданным значениям для изменения участника.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping("/{id}/update")
    public TeamMemberDto updateTeamMember(
            @PathVariable Long id,
            @RequestBody CreateUpdateTeamMemberDto createUpdateTeamMemberDto
    ) throws NotFoundException {
        return service.updateTeamMember(id, createUpdateTeamMemberDto);
    }

    /**
     * Метод для перевода участника в другую спортивную команду.
     *
     * @param id            идентификатор участника.
     * @param moveMemberDto данные о новой команде.
     * @return обновленная информация об участнике команды.
     * @throws NotFoundException возникает, если не удалось найти участника и/или команду.
     */
    @Operation(summary = "Перевести участника в другую команду.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Участник успешно переведен."),
            @ApiResponse(responseCode = "404", description = "Участник и/или команда не найдены.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping("/{id}/move")
    public TeamMemberDto moveTeamMember(
            @PathVariable Long id,
            @RequestBody MoveMemberDto moveMemberDto
    ) throws NotFoundException {
        return service.moveTeamMember(id, moveMemberDto);
    }

}
