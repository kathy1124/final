package im.ntub.myfinal2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

data class Contact(
    val name: String,
    val phone: String
)


class MainActivity : AppCompatActivity() {
    internal val viewModel: ContactViewModel by viewModels()
    private lateinit var viewPager2: ViewPager2
    private lateinit var btnNav: BottomNavigationView
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewPager2)
        btnNav = findViewById(R.id.bottomNavigationView)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager2.adapter = viewPagerAdapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnNav.selectedItemId = when (position) {
                    0 -> R.id.btnLeft
                    1 -> R.id.btnMiddle
                    2 -> R.id.btnRight
                    else -> R.id.btnLeft
                }
            }
        })

        btnNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btnLeft -> viewPager2.currentItem = 0
                R.id.btnMiddle -> viewPager2.currentItem = 1
                R.id.btnRight -> viewPager2.currentItem = 2
            }
            true
        }
    }
}
