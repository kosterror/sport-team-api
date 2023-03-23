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
import ru.kosterror.sportteamapi.dto.teammemberrole.NewTeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.TeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.UpdateTeamMemberRoleDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.service.teammemberrole.TeamMemberRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/team-member-roles")
@RequiredArgsConstructor
@Tag(name = "Работа с ролями участников команд")
public class TeamMemberRoleController {

    private final TeamMemberRoleService service;

    /**
     * Метод для получения всех ролей.
     *
     * @return список всех ролей.
     */
    @GetMapping
    @Operation(summary = "Получить список всех ролей участников команд.")
    @ApiResponse(responseCode = "200", description = "Данные получены успешно.")
    public List<TeamMemberRoleDto> getTeamMemberRoles() {
        return service.getTeamMemberRoles();
    }

    /**
     * Метод для получения роли по идентификатору.
     *
     * @param id идентификатор роли.
     * @return информация о найденной роли.
     * @throws NotFoundException возникает, если роль с таким идентификатором не найдена.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получить роль по идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены успешно."),
            @ApiResponse(responseCode = "404", description = "Роль по заданному идентификатору не найдена.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public TeamMemberRoleDto getTeamMemberRole(@PathVariable Long id) throws NotFoundException {
        return service.getTeamMemberRoleById(id);
    }

    /**
     * Метод для создания новой роли.
     *
     * @param newTeamMemberRoleDto данные новой роли.
     * @return данные о созданной роли.
     * @throws ConflictException возникает, если роль с подобными данными уже существует.
     */
    @PostMapping
    @Operation(summary = "Создать новую роль.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль успешно создана."),
            @ApiResponse(responseCode = "409", description = "Роль с такими данными уже существует.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public TeamMemberRoleDto createTeamMemberRole(@RequestBody NewTeamMemberRoleDto newTeamMemberRoleDto
    ) throws ConflictException {
        return service.createTeamMemberRole(newTeamMemberRoleDto);
    }

    /**
     * Метод для обновления данных о роли.
     *
     * @param id                      идентификатор обновляемой роли.
     * @param updateTeamMemberRoleDto новые данные роли.
     * @return сохраненная информация об обновленной роли.
     * @throws NotFoundException возникает, если роль с переданным идентификатором не найдена.
     * @throws ConflictException возникает, если роль с подобными данными уже существует.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные роли.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль успешно изменена."),
            @ApiResponse(responseCode = "404", description = "Роль с таки идентификатором не найдена.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Роль с такими данными уже существует.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public TeamMemberRoleDto updateTeamMemberRole(@PathVariable Long id,
                                                  @RequestBody UpdateTeamMemberRoleDto updateTeamMemberRoleDto
    ) throws NotFoundException, ConflictException {
        return service.updateTeamMemberRole(id, updateTeamMemberRoleDto);
    }

    /**
     * Метод для удаления роли по идентификатору.
     *
     * @param id идентификатор удаляемой роли.
     * @throws NotFoundException возникает, если роль с переданным идентификатором не найдена.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить роль по идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль успешно удалена."),
            @ApiResponse(responseCode = "404", description = "Роль с таким идентификатором не найдена.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void deleteTeamMemberRole(@PathVariable Long id) throws NotFoundException {
        service.deleteTeamMemberRole(id);
    }

}
