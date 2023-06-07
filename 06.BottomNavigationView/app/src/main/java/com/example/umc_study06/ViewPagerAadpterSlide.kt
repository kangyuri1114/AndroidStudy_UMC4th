import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.umc_study06.*

class ViewPagerAdapterSlide (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Slide1Fragment()
            1 -> Slide2Fragment()
            2 -> Slide3Fragment()
            else -> throw IllegalArgumentException("Invalid position: $position")

        }
    }
}