package com.example.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinpractice.databinding.ActivityRvBinding

data class Person (val name:String, val age:Int)

class RvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contacts: List<Person> = createContacts()

        binding.rvContacts.adapter = RVAdapter(this, contacts)
        binding.rvContacts.layoutManager = LinearLayoutManager(this)
    }

    private fun createContacts(): List<Person> {
        val contacts:MutableList<Person> = mutableListOf()
        for (i in 1..100){
            contacts.add(Person("Person $i",i))
        }
        return contacts
    }
}