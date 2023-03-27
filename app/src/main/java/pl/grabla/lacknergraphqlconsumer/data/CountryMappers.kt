package pl.grabla.lacknergraphqlconsumer.data

import pl.grabla.lacknergraphqlconsumer.domain.DetailedCountry
import pl.grabla.lacknergraphqlconsumer.domain.SimpleCountry
import pl.grabla.lacknergraphqlconsumer.generated.CountriesQuery
import pl.grabla.lacknergraphqlconsumer.generated.CountryQuery

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry{
    return DetailedCountry(
        code = code,
        capital = capital?:"No capital",
        continent = continent.name,
        currency = currency?:"No currency",
        emoji = emoji,
        name = name,
        languages = languages.mapNotNull { it.name }
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        capital = capital?:"No capital",
        emoji = emoji,
        name = name
    )
}