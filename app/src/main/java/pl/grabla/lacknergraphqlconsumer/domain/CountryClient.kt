package pl.grabla.lacknergraphqlconsumer.domain

import pl.grabla.lacknergraphqlconsumer.generated.CountryQuery

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}