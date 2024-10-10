package com.example.practicanavegacion.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavegacion.ui.Person

class PersonViewModel : ViewModel() {
    private val _likedPersons = MutableLiveData<List<Person>>()
    val likedPersons: LiveData<List<Person>> get() = _likedPersons

    private val _allPersons = listOf(
        Person("Estelita", listOf("estela", "estela1", "estela2", "estela3", "estela4")),
        Person("Toby", listOf("toby", "toby1", "toby2", "toby3")),
        Person("Pepe", listOf("pepe", "pepe1", "pepe2")),
        Person("Cachuchin", listOf("cachuchin", "cachuchin1", "cachuchin2", "cachuchin3")),
        Person("Cat Noir", listOf("cartnoir", "catnoir1", "catnoir2")),
        Person("Dogy Ramos", listOf("dogy", "dogy1")),
        Person("Michifu", listOf("michifu", "michifu1")),
    )

    fun addLikedPerson(person: Person) {
        val currentList = _likedPersons.value ?: emptyList()
        _likedPersons.value = currentList + person
    }

    fun getUnlikedPersons(): List<Person> {
        val likedPersonsSet = _likedPersons.value?.toSet() ?: emptySet()
        return _allPersons.filter { it !in likedPersonsSet }
    }
}