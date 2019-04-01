import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.application.bases.BaseFragment
import com.dionisiofilho.sicoob.application.helpers.ImageHelper


class SlideFragment : BaseFragment() {

    private lateinit var imageSlide: ImageView

    companion object {

        private const val URL = "url"

        @JvmStatic
        fun newInstance(urlImage: String) =
                SlideFragment().apply {
                    arguments = Bundle().apply {
                        putString(URL, urlImage)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        argumentsFromBundle()
    }

    private fun argumentsFromBundle() {
        arguments?.let {
            val url = it[URL] as String
            ImageHelper.getImageFromImageView(url, imageSlide)

        }
    }

    private fun initViews(view: View) {
        imageSlide = view.findViewById(R.id.iv_slide)
    }
}
