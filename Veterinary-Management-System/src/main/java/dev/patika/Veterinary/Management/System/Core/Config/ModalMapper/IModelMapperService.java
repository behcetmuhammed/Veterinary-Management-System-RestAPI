package dev.patika.Veterinary.Management.System.Core.Config.ModalMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
