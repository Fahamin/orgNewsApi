package com.news.livenews.worldwidenews.view

import ArticleAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahamin.hiltmvvmkotlincoroutin.viewModel.MainActivityViewModel
import com.google.android.material.navigation.NavigationView
import com.news.livenews.worldwidenews.Constance.ABC
import com.news.livenews.worldwidenews.Constance.API_KEY
import com.news.livenews.worldwidenews.Constance.API_KEY2
import com.news.livenews.worldwidenews.Constance.API_KEY3
import com.news.livenews.worldwidenews.Constance.API_KEY4
import com.news.livenews.worldwidenews.Constance.AUSTRALIA
import com.news.livenews.worldwidenews.Constance.AUSTRLIANFINACIAL
import com.news.livenews.worldwidenews.Constance.BBC
import com.news.livenews.worldwidenews.Constance.BRAZIL
import com.news.livenews.worldwidenews.Constance.CHINA
import com.news.livenews.worldwidenews.Constance.CNN
import com.news.livenews.worldwidenews.Constance.CRPYPTO
import com.news.livenews.worldwidenews.Constance.DAILYMAIL
import com.news.livenews.worldwidenews.Constance.ESPN
import com.news.livenews.worldwidenews.Constance.EYGPT
import com.news.livenews.worldwidenews.Constance.FOX
import com.news.livenews.worldwidenews.Constance.GURDIAN
import com.news.livenews.worldwidenews.Constance.JAPNA
import com.news.livenews.worldwidenews.Constance.MEXICO
import com.news.livenews.worldwidenews.Constance.NEWYORKAGAZINE
import com.news.livenews.worldwidenews.Constance.NEWZELAND
import com.news.livenews.worldwidenews.Constance.RAUSIA
import com.news.livenews.worldwidenews.Constance.SAUDY
import com.news.livenews.worldwidenews.Constance.SOUTHAFFRICA
import com.news.livenews.worldwidenews.Constance.TURKEY
import com.news.livenews.worldwidenews.Constance.UAE
import com.news.livenews.worldwidenews.Constance.USA
import com.news.livenews.worldwidenews.Constance.USATODAY
import com.news.livenews.worldwidenews.Constance.WALLSTREET
import com.news.livenews.worldwidenews.Constance.WASHINGTON
import com.news.livenews.worldwidenews.R
import com.news.livenews.worldwidenews.databinding.ActivityMainBinding
import com.news.livenews.worldwidenews.interfaceall.ItemClickListener
import com.news.livenews.worldwidenews.model.Articles
import com.news.livenews.worldwidenews.model.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    ItemClickListener {

    lateinit var list: List<Articles>
    private lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var articleAdapter: ArticleAdapter
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting custom ActionBar
        val toolbar: Toolbar = (binding.appBarMain.toolbar)
        setSupportActionBar(toolbar)

        // Showing the burger button on the ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        drawerLayout = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle navigation click events
        val navigationView: NavigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this);
        list = ArrayList()
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getCountryNews(USA, API_KEY3)
        setCountryNews()
    }

    fun setCountryNews() {
        list = ArrayList()
        mainActivityViewModel.countryNews.observe(this)
        {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.appBarMain.contenMain.progressbar.isVisible = it.isLoading
                }
                is NetworkResult.Failure -> {
                    binding.appBarMain.contenMain.progressbar.isVisible = false
                }
                is NetworkResult.Success -> {
                    list = it.data.body()!!.articles
                    articleAdapter = ArticleAdapter(list, this)
                    binding.appBarMain.contenMain.rvMovies.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = articleAdapter
                    }
                    binding.appBarMain.contenMain.progressbar.isVisible = false

                }
            }
        }
    }

    fun setPaperNews() {
        list = ArrayList()

        mainActivityViewModel.paperNews.observe(this)
        {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.appBarMain.contenMain.progressbar.isVisible = it.isLoading
                }
                is NetworkResult.Failure -> {
                    binding.appBarMain.contenMain.progressbar.isVisible = false
                }
                is NetworkResult.Success -> {
                    list = it.data.body()!!.articles

                    articleAdapter = ArticleAdapter(list, this)
                    binding.appBarMain.contenMain.rvMovies.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = articleAdapter
                    }
                    binding.appBarMain.contenMain.progressbar.isVisible = false

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.abcnews -> {
                mainActivityViewModel.getPaperNews(ABC, API_KEY2)
                setPaperNews()
            }
            R.id.bbc -> {
                mainActivityViewModel.getPaperNews(BBC, API_KEY2)
                setPaperNews()
            }
            R.id.australiafinan -> {
                mainActivityViewModel.getPaperNews(AUSTRLIANFINACIAL, API_KEY2)
                setPaperNews()
            }
            R.id.foxID -> {
                mainActivityViewModel.getPaperNews(FOX, API_KEY2)
                setPaperNews()
            }
            R.id.wasintonPost -> {
                mainActivityViewModel.getPaperNews(WASHINGTON, API_KEY)
                setPaperNews()
            }
            R.id.usaToday -> {
                mainActivityViewModel.getPaperNews(USATODAY, API_KEY)
                setPaperNews()
            }
            R.id.wallStreet -> {
                mainActivityViewModel.getPaperNews(WALLSTREET, API_KEY)
                setPaperNews()
            }
            R.id.gurdianID -> {
                mainActivityViewModel.getPaperNews(GURDIAN, API_KEY)
                setPaperNews()
            }
            R.id.cnnID -> {
                mainActivityViewModel.getPaperNews(CNN, API_KEY)
                setPaperNews()
            }
            R.id.cryptoID -> {
                mainActivityViewModel.getPaperNews(CRPYPTO, API_KEY3)
                setPaperNews()
            }
            R.id.dailymailID -> {
                mainActivityViewModel.getPaperNews(DAILYMAIL, API_KEY3)
                setPaperNews()
            }
            R.id.espnID -> {
                mainActivityViewModel.getPaperNews(ESPN, API_KEY3)
                setPaperNews()
            }
            R.id.newMagineID -> {
                mainActivityViewModel.getPaperNews(NEWYORKAGAZINE, API_KEY3)
                setPaperNews()
            }
            R.id.australiaID -> {
                mainActivityViewModel.getCountryNews(AUSTRALIA, API_KEY3)
                setCountryNews()
            }
            R.id.braxilID -> {
                mainActivityViewModel.getCountryNews(BRAZIL, API_KEY3)
                setCountryNews()
            }
            R.id.usaID -> {
                mainActivityViewModel.getCountryNews(USA, API_KEY2)
                setCountryNews()
            }
            R.id.rausiaID -> {
                mainActivityViewModel.getCountryNews(RAUSIA, API_KEY2)
                setCountryNews()
            }
            R.id.turkeyID -> {
                mainActivityViewModel.getCountryNews(TURKEY, API_KEY4)
                setCountryNews()
            }
            R.id.chinaID -> {
                mainActivityViewModel.getCountryNews(CHINA, API_KEY4)
                setCountryNews()
            }
            R.id.uaeID -> {
                mainActivityViewModel.getCountryNews(UAE, API_KEY4)
                setCountryNews()
            }
            R.id.egyptID -> {
                mainActivityViewModel.getCountryNews(EYGPT, API_KEY4)
                setCountryNews()
            }
            R.id.newzelanID -> {
                mainActivityViewModel.getCountryNews(NEWZELAND, API_KEY4)
                setCountryNews()
            }
            R.id.mexicoID -> {
                mainActivityViewModel.getCountryNews(MEXICO, API_KEY4)
                setCountryNews()
            }
            R.id.southaffircaID -> {
                mainActivityViewModel.getCountryNews(SOUTHAFFRICA, API_KEY4)
                setCountryNews()
            }
            R.id.japanID -> {
                mainActivityViewModel.getCountryNews(JAPNA, API_KEY4)
                setCountryNews()
            }
            R.id.suadiID -> {
                mainActivityViewModel.getCountryNews(SAUDY, API_KEY4)
                setCountryNews()
            }

        }
        drawerLayout.closeDrawer(Gravity.LEFT)
        return true
    }

    override fun itemClick(pos: Int) {
        val intent = Intent(this, WebActivity::class.java)
        intent.putExtra("url", list[pos].url)
        startActivity(intent)
    }
}