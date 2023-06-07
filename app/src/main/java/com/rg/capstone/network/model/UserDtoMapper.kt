package com.rg.capstone.network.model

import com.rg.capstone.domain.model.User
import com.rg.capstone.domain.util.DomainMapper

class UserDtoMapper : DomainMapper<UserDto, User> {
    override fun mapToDomainModel(model: UserDto): User {
        return User(
            id = model.id,
            email = model.email,
            name = model.name,
            height = model.height,
            weight = model.weight,
            gender = model.gender,
            dob = model.dob,
            age = model.age,
        )
    }

    override fun mapFromDomainModel(domainModel: User): UserDto {
        return UserDto(
            id = domainModel.id,
            email = domainModel.email,
            name = domainModel.name,
            height = domainModel.height,
            weight = domainModel.weight,
            gender = domainModel.gender,
            dob = domainModel.dob,
            age = domainModel.age,
        )
    }
}