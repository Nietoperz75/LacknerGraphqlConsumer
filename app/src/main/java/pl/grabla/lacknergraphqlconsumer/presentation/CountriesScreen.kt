package pl.grabla.lacknergraphqlconsumer.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.grabla.lacknergraphqlconsumer.domain.SimpleCountry

@Composable
fun CountriesScreen(
     state: CountriesViewModel.CountriesState,
     onSelectCountry: (code:String) -> Unit,
     onDismissCountryDialog: ()->Unit
) {
    Box(
         modifier = Modifier.fillMaxSize()
    ){
         if(state.isLoading){
              CircularProgressIndicator(
                   modifier = Modifier.align(Alignment.Center)
              )
         }else{
              LazyColumn(modifier = Modifier.fillMaxSize()){
                   items(state.countries){country ->

                   }
              }
         }
    }
}

@Composable
private fun CountryItem(
     country: SimpleCountry,
     modifier: Modifier = Modifier
){
         Row(
              modifier = modifier,
              verticalAlignment = Alignment.CenterVertically
         ) {
               Text(
                    text = country.emoji,
                    fontSize = 30.sp
               )
              Spacer(modifier = Modifier.width(16.dp))
              Column(
                   modifier = Modifier.weight(1f)
              ){
                    Text(
                         text = country.name
                    )
              }
         }
}