import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.summer_internship.CourseAbout
import com.example.summer_internship.CourseLessons
import com.example.summer_internship.CourseReviews

class MainCourse_ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3 // Number of fragments
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CourseAbout()
            1 -> CourseLessons()
            2 -> CourseReviews()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}