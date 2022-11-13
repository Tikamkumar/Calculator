package com.tikamkumar.newcalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import com.tikamkumar.newcalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This function use to set navigation drawer
        navigationDrawer()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.developer, R.id.exit
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        // This Buttons use to calculator
        binding.one.setOnClickListener {
            setText("1")
        }

        binding.two.setOnClickListener {
            setText("2")
        }

        binding.three.setOnClickListener {
            setText("3")
        }

        binding.four.setOnClickListener {
            setText("4")
        }

        binding.five.setOnClickListener {
            setText("5")
        }

        binding.six.setOnClickListener {
            setText("6")
        }

        binding.seven.setOnClickListener {
            setText("7")
        }

        binding.eight.setOnClickListener {
            setText("8")
        }

        binding.nine.setOnClickListener {
            setText("9")
        }

        binding.zero.setOnClickListener {
            setText("0")
        }

        binding.dot.setOnClickListener {
            setText(".")
        }

        binding.startbracket.setOnClickListener {
            setText("(")
        }

        binding.closebracket.setOnClickListener {
            setText(")")
        }

        binding.minus.setOnClickListener {
            setText("-")
        }

        binding.plus.setOnClickListener {
            setText("+")
        }

        binding.multi.setOnClickListener {
            setText("*")
        }

        binding.division.setOnClickListener {
            setText("/")
        }

        binding.doubleZero.setOnClickListener {
            setText("00")
        }

        binding.allclear.setOnClickListener {
            allClear()
        }

        binding.clear.setOnClickListener {
            singleClear()
        }

        binding.equal.setOnClickListener {
            setEqualFunction()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun singleClear() {
        val length = binding.inputtext.length()
        if (binding.inputtext.length() > 0)
            binding.inputtext.text = "${binding.inputtext.text.subSequence(0, length - 1)}"
        if (binding.inputtext.text.isEmpty()) {
            binding.outputtext.text = ""
        }
    }

    private fun allClear() {
        binding.inputtext.text = ""
        binding.outputtext.text = ""
    }

    @SuppressLint("SetTextI18n")
    private fun setEqualFunction() {
        try {
            if (binding.inputtext.length() > 0) {
                val expression = ExpressionBuilder(binding.inputtext.text.toString()).build()
                val result = expression.evaluate()
                val longresult = result.toLong()
                if (result == longresult.toDouble()) {
                    binding.outputtext.text =getString(R.string.Equal) + longresult.toString()
                } else {
                    binding.outputtext.text =getString(R.string.Equal) + result.toString()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Invalid format used", Toast.LENGTH_SHORT).show()
            binding.outputtext.text = ""
        }
    }

    private fun setText(text: String) {
        if (binding.inputtext.length() > 49) {
            Toast.makeText(
                this@MainActivity,
                "Can't enter more than 50 characters",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            binding.inputtext.append(text)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.blue ->{
                changeColor("#FFAA00FF")
                binding.linear.setBackgroundColor(Color.parseColor("#AE6200EE"))
                lineChangeColor("#84FFFF")
            }

            R.id.white -> {
                changeColor("#FFFFFFFF")
                binding.linear.setBackgroundColor(Color.parseColor("#e2e2e2"))
                lineChangeColor("#e2e2e2")
            }

            R.id.green ->{
                changeColor("#FF00C853")
                binding.linear.setBackgroundColor(Color.parseColor("#33691E"))
                lineChangeColor("#F1F8E9")
            }

            R.id.purple -> {
                changeColor("#BB86FC")
                binding.linear.setBackgroundColor(Color.parseColor("#AE6200EE"))
                lineChangeColor("#84FFFF")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun lineChangeColor(color: String) {
        binding.line1.setBackgroundColor(Color.parseColor(color))
        binding.line2.setBackgroundColor(Color.parseColor(color))
        binding.line3.setBackgroundColor(Color.parseColor(color))
        binding.line4.setBackgroundColor(Color.parseColor(color))
        binding.line5.setBackgroundColor(Color.parseColor(color))
        binding.line6.setBackgroundColor(Color.parseColor(color))
        binding.line7.setBackgroundColor(Color.parseColor(color))
        binding.line8.setBackgroundColor(Color.parseColor(color))
        binding.line9.setBackgroundColor(Color.parseColor(color))
        binding.line10.setBackgroundColor(Color.parseColor(color))
        binding.line11.setBackgroundColor(Color.parseColor(color))
    }

    private fun changeColor(color : String) {
        binding.parent.setBackgroundColor(Color.parseColor(color))
        binding.one.setBackgroundColor(Color.parseColor(color))
        binding.two.setBackgroundColor(Color.parseColor(color))
        binding.three.setBackgroundColor(Color.parseColor(color))
        binding.four.setBackgroundColor(Color.parseColor(color))
        binding.five.setBackgroundColor(Color.parseColor(color))
        binding.six.setBackgroundColor(Color.parseColor(color))
        binding.seven.setBackgroundColor(Color.parseColor(color))
        binding.eight.setBackgroundColor(Color.parseColor(color))
        binding.nine.setBackgroundColor(Color.parseColor(color))
        binding.dot.setBackgroundColor(Color.parseColor(color))
        binding.doubleZero.setBackgroundColor(Color.parseColor(color))
        binding.equal.setBackgroundColor(Color.parseColor(color))
        binding.plus.setBackgroundColor(Color.parseColor(color))
        binding.minus.setBackgroundColor(Color.parseColor(color))
        binding.multi.setBackgroundColor(Color.parseColor(color))
        binding.division.setBackgroundColor(Color.parseColor(color))
        binding.startbracket.setBackgroundColor(Color.parseColor(color))
        binding.closebracket.setBackgroundColor(Color.parseColor(color))
        binding.allclear.setBackgroundColor(Color.parseColor(color))
        binding.zero.setBackgroundColor(Color.parseColor(color))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exit ->{
                finish()
            }
            R.id.developer -> {
                val intent = Intent(this@MainActivity, Developer::class.java)
                startActivity(intent)
                finish()
            }
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawer : DrawerLayout = findViewById(R.id.drawer_layout)
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
    private fun navigationDrawer(){
        val drawer : DrawerLayout = findViewById(R.id.drawer_layout)
        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(this,drawer,binding.toolbar,
            R.string.Open,
            R.string.Close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = ResourcesCompat.getColor(resources, R.color.white,null)
        toggle.syncState()
    }

}