import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.summer_internship.CompletedMyCourse
import com.example.summer_internship.OngoingMyCourse

class MyCourse_ViewPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2 // Number of fragments
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OngoingMyCourse()
            1 -> CompletedMyCourse()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}