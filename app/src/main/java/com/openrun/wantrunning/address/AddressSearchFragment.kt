package com.openrun.wantrunning.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.openrun.wantrunning.R
import com.openrun.wantrunning.base.ui.compose.BoxedTextField
import com.openrun.wantrunning.databinding.FragmentAddressSearchBinding
import com.openrun.wantrunning.ui.BasicButton
import com.openrun.wantrunning.ui.WantRunningTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressSearchFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentAddressSearchBinding? = null
    private val binding: FragmentAddressSearchBinding get() = _binding!!

    private val viewModel by viewModels<AddressSearchViewModel>()

    private lateinit var naverMapInstance: NaverMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddressSearchBinding.inflate(inflater, container, false)
        binding.composeAddressSearchBottomSheet.setContent {
            WantRunningTheme {
                AddressSearchBottomSheet(modifier = Modifier.padding(all = 16.dp))
            }
        }
        initNaverMapFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbAddressSearch.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        naverMapInstance = naverMap
    }

    private fun initNaverMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.fcv_address_search_map) as MapFragment?
        (mapFragment ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().add(R.id.fcv_address_search_map, it).commit()
        }).getMapAsync(this)
    }
}

@Composable
private fun AddressSearchBottomSheet(modifier: Modifier = Modifier) {
    val address: String by remember { mutableStateOf("서울특별시 마포구 양화로 160 홍대입구역") }
    var detail: String by remember { mutableStateOf("") }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        Text(text = address, style = MaterialTheme.typography.body2)

        BoxedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = detail,
            onValueChange = { detail = it },
            hint = "상세 위치를 입력해주세요. (예: 4번 출구 앞)"
        )

        BasicButton(text = "설정 완료", onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun AddressSearchBottomSheetPreview() {
    WantRunningTheme {
        AddressSearchBottomSheet()
    }
}
