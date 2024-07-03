package com.sovereign.netclanexplorercopy

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var refine: ImageView
    private lateinit var refine2: TextView

    private lateinit var exploreIcon: ImageView
    private lateinit var connectIcon: ImageView
    private lateinit var chatIcon: ImageView
    private lateinit var contactIcon: ImageView
    private lateinit var groupsIcon: ImageView

    private lateinit var exploreText: TextView
    private lateinit var connectText: TextView
    private lateinit var chatText: TextView
    private lateinit var contactText: TextView
    private lateinit var groupsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tab_layout)
        refine = findViewById(R.id.refine_icon)
        refine2 = findViewById(R.id.refine_text)

        exploreIcon = findViewById(R.id.explore)
        connectIcon = findViewById(R.id.connect_icon)
        chatIcon = findViewById(R.id.chat_icon)
        contactIcon = findViewById(R.id.contact_icon)
        groupsIcon = findViewById(R.id.groups_icon)

        exploreText = findViewById(R.id.explore_text)
        connectText = findViewById(R.id.connect_text)
        chatText = findViewById(R.id.chat_text)
        contactText = findViewById(R.id.contact_text)
        groupsText = findViewById(R.id.groups_text)

        initialSelection(exploreIcon, exploreText)

        exploreIcon.setOnClickListener {
            toggleSelection(exploreIcon, exploreText)
            tabLayout.visibility = View.VISIBLE
            viewPager.currentItem = 0
        }
        connectIcon.setOnClickListener {
            toggleSelection(connectIcon, connectText)
            tabLayout.visibility = View.GONE
            replaceFragment(connections())
        }
        chatIcon.setOnClickListener {
            toggleSelection(chatIcon, chatText)
            tabLayout.visibility = View.GONE
            replaceFragment(chat())
        }
        contactIcon.setOnClickListener {
            toggleSelection(contactIcon, contactText)
            tabLayout.visibility = View.GONE
            replaceFragment(contacts())
        }
        groupsIcon.setOnClickListener {
            toggleSelection(groupsIcon, groupsText)
            tabLayout.visibility = View.GONE
            replaceFragment(groups())
        }

        exploreText.setOnClickListener {
            toggleSelection(exploreIcon, exploreText)
            tabLayout.visibility = View.VISIBLE
            viewPager.currentItem = 0
        }
        connectText.setOnClickListener {
            toggleSelection(connectIcon, connectText)
            tabLayout.visibility = View.GONE
            replaceFragment(connections())
        }
        chatText.setOnClickListener {
            toggleSelection(chatIcon, chatText)
            tabLayout.visibility = View.GONE
            replaceFragment(chat())
        }
        contactText.setOnClickListener {
            toggleSelection(contactIcon, contactText)
            tabLayout.visibility = View.GONE
            replaceFragment(contacts())
        }
        groupsText.setOnClickListener {
            toggleSelection(groupsIcon, groupsText)
            tabLayout.visibility = View.GONE
            replaceFragment(groups())
        }

        refine.setOnClickListener {
            val intent = Intent(this, Refine::class.java)
            startActivity(intent)
        }

        refine2.setOnClickListener {
            val intent = Intent(this, Refine::class.java)
            startActivity(intent)
        }


        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


    }


    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> PersonalFragment()
                1 -> ServicesFragment()
                2 -> BusinessFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Personal"
                1 -> "Services"
                2 -> "Businesses"
                else -> null
            }
        }
    }


    private fun initialSelection(icon: ImageView, text: TextView) {
        icon.setColorFilter(ContextCompat.getColor(this, R.color.text), PorterDuff.Mode.SRC_IN)
        text.setTextColor(ContextCompat.getColor(this, R.color.text))
    }

    private fun toggleSelection(selectedIcon: ImageView, selectedText: TextView) {
        deselectAllIconsAndTexts()

        selectedIcon.setColorFilter(ContextCompat.getColor(this, R.color.text), PorterDuff.Mode.SRC_IN)
        selectedText.setTextColor(ContextCompat.getColor(this, R.color.text))

        when (selectedIcon) {
            exploreIcon -> {
                tabLayout.visibility = View.VISIBLE
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            connectIcon -> {
                tabLayout.visibility = View.GONE
                replaceFragment(connections())
            }
            chatIcon -> {
                tabLayout.visibility = View.GONE
                replaceFragment(chat())
            }
            contactIcon -> {
                tabLayout.visibility = View.GONE
                replaceFragment(contacts())
            }
            groupsIcon -> {
                tabLayout.visibility = View.GONE
                replaceFragment(groups())
            }
        }
    }

    private fun deselectAllIconsAndTexts() {
        exploreIcon.clearColorFilter()
        connectIcon.clearColorFilter()
        chatIcon.clearColorFilter()
        contactIcon.clearColorFilter()
        groupsIcon.clearColorFilter()

        exploreText.setTextColor(ContextCompat.getColor(this, R.color.text_unselected))
        connectText.setTextColor(ContextCompat.getColor(this, R.color.text_unselected))
        chatText.setTextColor(ContextCompat.getColor(this, R.color.text_unselected))
        contactText.setTextColor(ContextCompat.getColor(this, R.color.text_unselected))
        groupsText.setTextColor(ContextCompat.getColor(this, R.color.text_unselected))
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

}
