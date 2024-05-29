package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentHomeBinding
import com.example.makaryoapps.ui.category.CategoryAdapter
import com.example.makaryoapps.ui.category.CategoryModel
import com.example.makaryoapps.ui.promopager.ImagePromoAdapter
import com.example.makaryoapps.ui.promopager.PromoModel
import com.example.makaryoapps.ui.recomended.RecomendedAdapter
import com.example.makaryoapps.ui.recomended.RecomendedModel
import java.util.UUID


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    /*private lateinit var viewpager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback
*/
    private lateinit var secondAdapter: RecomendedAdapter
    private var dataSecond: MutableList<RecomendedModel> = mutableListOf()

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8, 0, 8, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirstRecyclerView()
        setupSecondRecyclerView()

        binding. tvNearest.setOnClickListener {
            // Atur warna teks TextView "Terdekat" menjadi warna primary
            binding.tvNearest.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            // Atur warna teks TextView "Rating" menjadi warna default
           binding. tvBtnRatting.setTextColor(ContextCompat.getColor(requireContext(),R.color.grey_font))
        }

        // Atur listener untuk TextView "Rating"
        binding.tvBtnRatting.setOnClickListener {
            sortByRating()
            // Atur warna teks TextView "Rating" menjadi warna primary
            binding.tvBtnRatting.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            // Atur warna teks TextView "Terdekat" menjadi warna default
            binding.tvNearest.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_font))
        }

        val imgSlider = binding.imageSlider
        val slides = ArrayList<SlideModel>()

        slides.add(SlideModel("https://img.freepik.com/free-vector/design-courses-sale-banner-template_23-2149044442.jpg?w=1060&t=st=1716639679~exp=1716640279~hmac=507d30108d3819929ec73ff4310a39dbb124f8d742f7a8eee0f0426336823655"))
        slides.add(SlideModel("https://i.pinimg.com/564x/52/be/ae/52beae20d30c524cc382a32086005823.jpg"))
        slides.add(SlideModel("https://fastly.picsum.photos/id/320/500/500.jpg?hmac=2iE7TIF9kIqQOHrIUPOJx2wP1CJewQIZBeMLIRrm74s"))
        imgSlider.setImageList(slides, ScaleTypes.CENTER_CROP)

    }

    private fun setupFirstRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataFirst = listOf(
            CategoryModel(R.drawable.builder,"Builder"),
            CategoryModel(R.drawable.electronic, "Electronic"),
            CategoryModel(R.drawable.cleaning,"Cleaning"),
            CategoryModel(R.drawable.otomotif,"Otomotif")
        )
        val firstAdapter = CategoryAdapter(dataFirst, object : CategoryAdapter.OnCategoryClickListener {
            override fun onCategoryClick(category: CategoryModel) {
                navigateToCategory(category)
            }
        })
        binding.recyclerView.adapter = firstAdapter
    }
private fun navigateToCategory(category: CategoryModel){
    val navController = findNavController()
    when (category.imageCategory){
        R.drawable.builder -> navController.navigate(R.id.action_homeFragment_to_builderFragment)
        R.drawable.electronic -> navController.navigate(R.id.action_homeFragment_to_electronicFragment)
        R.drawable.cleaning -> navController.navigate(R.id.action_homeFragment_to_cleaningFragment)
        R.drawable.otomotif -> navController.navigate(R.id.action_homeFragment_to_otomotifFragment)

        else ->Toast.makeText(requireContext(), "Kategori tidak ditemukan", Toast.LENGTH_SHORT).show()
    }
}
    private fun setupSecondRecyclerView() {
        binding.rvRekomendasi.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val photo = resources.obtainTypedArray(R.array.data_photo)
        val names = resources.getStringArray(R.array.data_craftsman)
        val skills = resources.getStringArray(R.array.data_skill)
        val ratingsStringArray = resources.getStringArray(R.array.data_star)
        val ratingsFloatArray = ratingsStringArray.map { it.toFloat() }.toFloatArray()


        dataSecond = names.indices.map { index ->
            RecomendedModel(
                photo.getResourceId(index, -1), // Ambil ID foto dengan indeks yang sesuai
                names[index], // Ambil nama dengan indeks yang sesuai
                skills[index], // Ambil keterampilan dengan indeks yang sesuai
                ratingsFloatArray[index], // Ambil rating dengan indeks yang sesuai (gunakan array float)
                R.drawable.ic_ratting // Gunakan gambar bintang yang sama untuk semua
            )
        }.toMutableList() // Ubah hasil map menjadi MutableList

        photo.recycle()

        secondAdapter = RecomendedAdapter(dataSecond)
        binding.rvRekomendasi.adapter = secondAdapter
    }

    private fun sortByRating() {
        val sortedList = dataSecond.sortedByDescending { it.nilaiRatting }
          secondAdapter.replaceData(sortedList)
    }
   /* private fun setupViewPagerWithIndicators() {
        viewpager2 = binding.viewpager2

        val imageList = arrayListOf(
            PromoModel(
                UUID.randomUUID().toString(),
                "https://img.freepik.com/free-vector/design-courses-sale-banner-template_23-2149044442.jpg?w=1060&t=st=1716639679~exp=1716640279~hmac=507d30108d3819929ec73ff4310a39dbb124f8d742f7a8eee0f0426336823655"
            ),
            PromoModel(
                UUID.randomUUID().toString(),
                "https://i.pinimg.com/564x/52/be/ae/52beae20d30c524cc382a32086005823.jpg"
            ),
            PromoModel(
                UUID.randomUUID().toString(),
                "https://fastly.picsum.photos/id/320/500/500.jpg?hmac=2iE7TIF9kIqQOHrIUPOJx2wP1CJewQIZBeMLIRrm74s"
            ),
            PromoModel(
                UUID.randomUUID().toString(),
                "https://fastly.picsum.photos/id/798/500/500.jpg?hmac=Bmzk6g3m8sUiEVHfJWBscr2DUg8Vd2QhN7igHBXLLfo"
            ),
            PromoModel(
                UUID.randomUUID().toString(),
                "https://fastly.picsum.photos/id/95/500/500.jpg?hmac=0aldBQ7cQN5D_qyamlSP5j51o-Og4gRxSq4AYvnKk2U"
            ),
            PromoModel(
                UUID.randomUUID().toString(),
                "https://fastly.picsum.photos/id/778/500/500.jpg?hmac=jZLZ6WV_OGRxAIIYPk7vGRabcAGAILzxVxhqSH9uLas"
            )
        )

        val imageAdapter = ImagePromoAdapter()
        viewpager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)


    }
*/

/*    private fun setupDotsIndicator(size: Int) {
        val dotsImage = Array(size) { ImageView(requireContext()) }
        dotsImage.forEach {
            it.setImageResource(R.drawable.slider)
            binding.slide.addView(it, params)
        }

        dotsImage[0].setImageResource(R.drawable.active_slider)

        pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dotsImage.forEachIndexed { index, imageView ->
                    imageView.setImageResource(if (index == position) R.drawable.active_slider else R.drawable.slider)
                }
                super.onPageSelected(position)
            }
        }
        viewpager2.registerOnPageChangeCallback(pageChangeListener)
    }*/


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        /*viewpager2.unregisterOnPageChangeCallback(pageChangeListener)*/
    }
}


