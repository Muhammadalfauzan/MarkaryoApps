package com.example.makaryoapps.ui.fragment

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentChatBinding
import com.example.makaryoapps.ui.chat.ChatAdapter
import com.example.makaryoapps.ui.chat.ChatModel

class ChatFragment : Fragment(), ChatAdapter.OnItemClickListener {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val dataChat: ArrayList<ChatModel> = ArrayList()

    private var currentAnimator: Animator? = null
    private val shortAnimationDuration: Int by lazy {
        resources.getInteger(android.R.integer.config_shortAnimTime)
    }

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatAdapter = ChatAdapter(this)

        binding.rvChat.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvChat.adapter = chatAdapter

        getDataChat()
    }

    private fun getDataChat() {
        val photo = resources.obtainTypedArray(R.array.data_photo_chat)
        val names = resources.getStringArray(R.array.data_craftsman_chat)
        val lastChat = resources.getStringArray(R.array.data_lastChat)
        val jam = resources.getStringArray(R.array.data_jam)

        val chatList = mutableListOf<ChatModel>()

        for (i in names.indices) {
            val chat = ChatModel(
                photo.getResourceId(i, -1),
                names[i],
                lastChat[i],
                jam[i]
            )
            chatList.add(chat)
        }
        photo.recycle()

        chatAdapter.submitList(chatList.distinct())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(chat: ChatModel) {
        Log.d("Item clicked", "Chat Item clicked")
        val bundle = bundleOf("item" to chat)
        findNavController().navigate(R.id.action_chatFragment_to_detailChatFragment, bundle)
    }
}
