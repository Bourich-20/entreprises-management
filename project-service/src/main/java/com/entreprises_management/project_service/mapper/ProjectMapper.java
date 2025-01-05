package com.entreprises_management.project_service.mapper;

import com.entreprises_management.project_service.dtos.ProjectCreationDTO;
import com.entreprises_management.project_service.dtos.ProjectDTO;
import com.entreprises_management.project_service.entitie.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;



@Mapper(componentModel = "spring")
public interface ProjectMapper {


    Project projectCreationDTOToProject(ProjectCreationDTO projectCreationDTO);

    ProjectDTO projectToProjectDTO(Project project);

    Project updateProjectFromDTO(ProjectCreationDTO projectCreationDTO, @MappingTarget Project project);
}
