package com.globalobjectdemo.app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globalobjectdemo.app.models.UserModel

class UserViewModel : ViewModel() {

    var userArrayList: MutableLiveData<ArrayList<UserModel>>? = MutableLiveData()
    var um = UserModel()


    fun getUsers() {

        if (userArrayList!!.value != null && userArrayList!!.value!!.size > 0) {
            userArrayList!!.value = userArrayList!!.value

        } else {
            var id = 0
            var tempArrayList: ArrayList<UserModel> = ArrayList()

            tempArrayList.add(addUser(id, "ABC", 32, "India"))
            id++
            tempArrayList.add(addUser(id, "DEF", 35, "Nigeria"))
            id++
            tempArrayList.add(addUser(id, "GHI", 30, "Kuwait"))
            id++
            tempArrayList.add(addUser(id, "JKL", 25, "Sudan"))
            id++
            tempArrayList.add(addUser(id, "MNO", 18, "America"))
            id++
            tempArrayList.add(addUser(id, "PQR", 37, "Spain"))
            id++
            tempArrayList.add(addUser(id, "STU", 29, "Ireland"))
            id++
            tempArrayList.add(addUser(id, "VWX", 25, "Netherlands"))
            id++
            tempArrayList.add(addUser(id, "YZ", 19, "Denmark"))

            userArrayList!!.value = tempArrayList
        }

    }

    private fun addUser(id: Int, name: String, age: Int, country: String): UserModel {

        um = UserModel()
        um.id = id
        um.name = name
        um.age = age
        um.country = country

        return um
    }

    fun getAge(id: Int): String {

        return userArrayList!!.value!![getIndex(id)].age.toString()
    }

    fun getName(id: Int): String {

        return userArrayList!!.value!![getIndex(id)].name
    }

    fun getCountry(id: Int): String {

        return userArrayList!!.value!![getIndex(id)].country
    }

    private fun getIndex(id: Int): Int {
//        Log.e(
//            "11111",
//            " id pressed: ===>>>>" + userArrayList!!.value!!.indexOfFirst { i -> i.id == id })
        return userArrayList!!.value!!.indexOfFirst { i -> i.id == id }
    }

    fun saveObject(userId: Int, age: String, name: String, country: String) {

        var temp = UserModel()

        var index = getIndex(userId)

        temp = userArrayList!!.value!![index]

        temp.age = Integer.parseInt(age)
        temp.name = name
        temp.country = country

        userArrayList!!.value!![index] = temp
    }
}