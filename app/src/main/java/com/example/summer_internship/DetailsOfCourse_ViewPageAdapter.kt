import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.summer_internship.CourseAbout
import com.example.summer_internship.CourseLessons
import com.example.summer_internship.DetailsOfCourse
import com.example.summer_internship.DetailsOfCourseCertification
import com.example.summer_internship.DetailsOfCourseLessons

class DetailsOfCourse_ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2 // Number of fragments
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailsOfCourseLessons()
            1 -> DetailsOfCourseCertification()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}