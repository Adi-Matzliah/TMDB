package com.exercise.firstdigitalbank.tmdb.data.mapper

interface IEntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}