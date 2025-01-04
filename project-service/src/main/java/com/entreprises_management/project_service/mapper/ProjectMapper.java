package com.entreprises_management.project_service.mapper;

import com.entreprises_management.project_service.dtos.ProjectCreationDTO;
import com.entreprises_management.project_service.dtos.ProjectDTO;
import com.entreprises_management.project_service.entitie.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO(Project project);

    List<ProjectDTO> toDTOList(List<Project> projects);

    Project toEntity(ProjectCreationDTO projectCreationDTO);
}