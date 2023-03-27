package pl.grabla.lacknergraphqlconsumer.data

import com.apollographql.apollo3.ApolloClient
import pl.grabla.lacknergraphqlconsumer.domain.CountryClient
import pl.grabla.lacknergraphqlconsumer.domain.DetailedCountry
import pl.grabla.lacknergraphqlconsumer.domain.SimpleCountry
import pl.grabla.lacknergraphqlconsumer.generated.CountriesQuery
import pl.grabla.lacknergraphqlconsumer.generated.CountryQuery

class ApolloCountryClient(
    private val apolloClient: ApolloClient
):CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map {
                it.toSimpleCountry()
            } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }

}