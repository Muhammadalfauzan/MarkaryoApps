package com.example.makaryoapps.ui.fragment

import com.example.makaryoapps.ui.costumdialogfragment.LocationPermissionDialogFragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentHomeBinding
import com.example.makaryoapps.ui.category.CategoryAdapter
import com.example.makaryoapps.ui.category.CategoryModel

import com.example.makaryoapps.ui.recomended.RecomendedAdapter
import com.example.makaryoapps.ui.recomended.RecomendedModel
import com.google.android.material.tabs.TabLayout

@Suppress("DEPRECATION")
class HomeFragment : Fragment(), RecomendedAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var secondAdapter: RecomendedAdapter
    private var dataSecond: MutableList<RecomendedModel> = mutableListOf()
    private val handler = Handler(Looper.getMainLooper())
    private var isLocationPermissionGranted: Boolean = false
    private val shimmerRunnable = Runnable {
        stopShimmerEffect()
    }
    private val bannerRunnable = Runnable {
        setupBanner()
    }

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSION = 100
        const val PREFS_NAME = "prefs"
        const val KEY_LOCATION_PERMISSION_GRANTED = "isLocationPermissionGranted"
        const val KEY_LOCATION_PERMISSION_DIALOG_SHOWN = "isLocationPermissionDialogShown"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirstRecyclerView()
        setupSecondRecyclerView()
        startShimmerEffect()
        refreshViewsBasedOnPermission()

        // Check if location permission dialog has been shown before and permission is not granted
        if (!isLocationPermissionDialogShown() && !isLocationPermissionGranted) {
            showLocationPermissionDialog()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> sortAll()
                    1 -> sortByNearest()
                    2 -> sortByRating()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun showLocationPermissionDialog() {
        val dialog = LocationPermissionDialogFragment()
        dialog.setTargetFragment(this, REQUEST_CODE_LOCATION_PERMISSION)
        dialog.show(parentFragmentManager, "com.example.makaryoapps.ui.costumdialogfragment.LocationPermissionDialogFragment")
    }

    private fun dismissLocationPermissionDialog() {
        val dialogFragment =
            parentFragmentManager.findFragmentByTag("com.example.makaryoapps.ui.costumdialogfragment.LocationPermissionDialogFragment") as? DialogFragment
        dialogFragment?.dismiss()
    }

    private fun refreshViewsBasedOnPermission() {
        isLocationPermissionGranted = sharedPreferences.getBoolean(KEY_LOCATION_PERMISSION_GRANTED, false)
        if (isLocationPermissionGranted) {
            binding.tabLayout.visibility = View.VISIBLE
            binding.imgLocation.visibility = View.VISIBLE
            binding.tvLocation.visibility = View.VISIBLE
            showTab()
            sortByNearest()
        } else {
            binding.tabLayout.visibility = View.GONE
            binding.imgLocation.visibility = View.GONE
            binding.tvLocation.visibility = View.GONE
            sortByRating()
        }
    }

    private fun isLocationPermissionDialogShown(): Boolean {
        return sharedPreferences.getBoolean(KEY_LOCATION_PERMISSION_DIALOG_SHOWN, false)
    }

    private fun saveLocationPermissionChoice(isGranted: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_LOCATION_PERMISSION_GRANTED, isGranted).apply()
    }

    fun setLocationPermission(isGranted: Boolean) {
        saveLocationPermissionChoice(isGranted)
        refreshViewsBasedOnPermission()
        if (!isGranted) {
            Toast.makeText(
                requireContext(),
                "Izin lokasi tidak diberikan, data terdekat tidak tersedia",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            val isGranted = data?.getBooleanExtra("isGranted", false) ?: false
            setLocationPermission(isGranted)
            if (isGranted) {
                setLocationPermission(true)
            }
        }
    }

    private fun showTab() {
        binding.tabLayout.removeAllTabs()

        val allTab = binding.tabLayout.newTab()
        allTab.text = "Semua"
        binding.tabLayout.addTab(allTab)

        val nearestTab = binding.tabLayout.newTab()
        nearestTab.text = "Terdekat"
        binding.tabLayout.addTab(nearestTab)

        val ratingTab = binding.tabLayout.newTab()
        ratingTab.text = "Rating"
        binding.tabLayout.addTab(ratingTab)
    }

    private fun setupFirstRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataFirst = listOf(
            CategoryModel(R.drawable.builder, "Builder"),
            CategoryModel(R.drawable.electronic, "Electronic"),
            CategoryModel(R.drawable.cleaning, "Cleaning"),
            CategoryModel(R.drawable.otomotif, "Otomotif")
        )
        val firstAdapter = CategoryAdapter(
            dataFirst,
            object : CategoryAdapter.OnCategoryClickListener {
                override fun onCategoryClick(category: CategoryModel) {
                    navigateToCategory(category)
                }
            })
        binding.recyclerView.adapter = firstAdapter
    }

    private fun navigateToCategory(category: CategoryModel) {
        val navController = findNavController()
        when (category.imageCategory) {
            R.drawable.builder -> navController.navigate(R.id.action_homeFragment_to_builderFragment)
            R.drawable.electronic -> navController.navigate(R.id.action_homeFragment_to_electronicFragment)
            R.drawable.cleaning -> navController.navigate(R.id.action_homeFragment_to_cleaningFragment)
            R.drawable.otomotif -> navController.navigate(R.id.action_homeFragment_to_otomotifFragment)
            else -> Toast.makeText(requireContext(), "Kategori tidak ditemukan", Toast.LENGTH_SHORT).show()
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
        val address = resources.getStringArray(R.array.data_alamat)

        // Placeholder distances, replace with actual data
        val distances = listOf(1.2f, 3.5f, 0.8f, 2.1f)

        dataSecond = names.indices.map { index ->
            RecomendedModel(
                photo.getResourceId(index, -1),
                names[index],
                skills[index],
                ratingsFloatArray[index],
                R.drawable.ic_ratting,
                distances.getOrElse(index) { 0f }, // Handle potential size mismatch
                address[index]
            )
        }.toMutableList()

        photo.recycle()

        secondAdapter = RecomendedAdapter(this)
        binding.rvRekomendasi.adapter = secondAdapter
        secondAdapter.submitList(dataSecond.toList()) // Ensure you submit a new list
    }

    private fun sortByRating() {
        val sortedList = dataSecond.sortedByDescending { it.nilaiRatting }
        secondAdapter.submitList(sortedList.toList()) // Ensure you submit a new list
    }

    private fun sortByNearest() {
        if (isLocationPermissionGranted) {
            val sortedList = dataSecond.sortedBy { it.distance }
            secondAdapter.submitList(sortedList.toList()) // Ensure you submit a new list
        } else {
            Toast.makeText(requireContext(), "Izin lokasi tidak diberikan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sortAll() {
        val sortedList = if (isLocationPermissionGranted) {
            dataSecond.sortedWith(compareBy({ it.distance }, { -it.nilaiRatting }))
        } else {
            dataSecond.sortedByDescending { it.nilaiRatting }
        }
        secondAdapter.submitList(sortedList.toList()) // Ensure you submit a new list
    }

    private fun startShimmerEffect() {
        binding.shimmerCategory.visibility = View.VISIBLE
        binding.shimmerCategory.startShimmer()
        binding.shimmerRekomendasi.visibility = View.VISIBLE
        binding.shimmerRekomendasi.startShimmer()
        binding.shimmerBanner.visibility = View.VISIBLE
        binding.shimmerBanner.startShimmer()
        binding.tabLayout.visibility = View.GONE
        binding.imgLocation.visibility = View.GONE
        binding.tvLocation.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.rvRekomendasi.visibility = View.GONE

        handler.postDelayed(shimmerRunnable, 3000)
    }

    private fun stopShimmerEffect() {
        _binding?.let {
            it.shimmerCategory.stopShimmer()
            it.shimmerCategory.visibility = View.GONE
            it.shimmerRekomendasi.stopShimmer()
            it.shimmerRekomendasi.visibility = View.GONE
            it.shimmerBanner.stopShimmer()
            it.shimmerBanner.visibility = View.GONE
            it.recyclerView.visibility = View.VISIBLE
            it.rvRekomendasi.visibility = View.VISIBLE
            it.tabLayout.visibility = if (isLocationPermissionGranted) View.VISIBLE else View.GONE
            setupBanner()
            handler.postDelayed(bannerRunnable, 3000)
        }
    }

    private fun setupBanner() {
        Handler(Looper.getMainLooper()).postDelayed({
            val imgSlider = binding.imageSlider
            val slides = ArrayList<SlideModel>()
            slides.add(SlideModel("https://img.freepik.com/free-vector/design-courses-sale-banner-template_23-2149044442.jpg?w=1060&t=st=1716639679~exp=1716640279~hmac=507d30108d3819929ec73ff4310a39dbb124f8d742f7a8eee0f0426336823655"))
            slides.add(SlideModel("https://i.pinimg.com/564x/52/be/ae/52beae20d30c524cc382a32086005823.jpg"))
            slides.add(SlideModel("https://fastly.picsum.photos/id/320/500/500.jpg?hmac=2iE7TIF9kIqQOHrIUPOJx2wP1CJewQIZBeMLIRrm74s"))
            imgSlider.setImageList(slides, ScaleTypes.CENTER_CROP)
            binding.imageSlider.visibility = View.VISIBLE
        }, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(shimmerRunnable)
        handler.removeCallbacks(bannerRunnable)
        _binding = null
    }

    override fun onItemClick(data: RecomendedModel) {
        val bundle = bundleOf("item" to data)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}
