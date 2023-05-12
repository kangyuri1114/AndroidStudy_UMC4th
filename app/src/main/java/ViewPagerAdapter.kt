import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.umc_study06.CameraFragment
import com.example.umc_study06.HomeFragment
import com.example.umc_study06.PlantFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CameraFragment()
            1 -> HomeFragment()
            else -> PlantFragment()
        }
    }
}