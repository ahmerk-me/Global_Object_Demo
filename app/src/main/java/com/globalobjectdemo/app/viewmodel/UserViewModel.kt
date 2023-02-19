package com.globalobjectdemo.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globalobjectdemo.app.models.UserModel

class UserViewModel: ViewModel() {

    var userArrayList: MutableLiveData<ArrayList<UserModel>> = MutableLiveData()
    var um = UserModel()


    fun getUsers() {

        var tempArrayList: ArrayList<UserModel> = ArrayList()

        tempArrayList.add(addUser("Ahmer K", 32, "India"))
        tempArrayList.add(addUser("Danish Z", 35, "Nigeria"))
        tempArrayList.add(addUser("Shahbaz S", 30, "Kuwait"))
        tempArrayList.add(addUser("Tahir K", 25, "Sudan"))
        tempArrayList.add(addUser("Aman W", 18, "America"))
        tempArrayList.add(addUser("Iksheer M", 37, "India"))
        tempArrayList.add(addUser("Prateek R", 29, "Ireland"))

        userArrayList.value = tempArrayList

    }

    private fun addUser(name: String, age: Int, country: String): UserModel {

        um.name = name
        um.age = age
        um.country = country

        return um
    }
}