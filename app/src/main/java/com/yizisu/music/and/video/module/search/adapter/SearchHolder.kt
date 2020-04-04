package com.yizisu.music.and.video.module.search.adapter

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.text.TextUtils
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.mvvm.MvvmPopupWindow
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.widget.BaseLinearLayout
import com.yizisu.basemvvm.widget.BaseTextView
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.CurrentPlayListDialog
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.add_song_to_album.AddSongToAlbumActivity
import com.yizisu.music.and.video.utils.DownSongWithNotification
import com.yizisu.music.and.video.utils.DownloadSongHelper
import com.yizisu.music.and.video.utils.dbViewModel
import com.yizisu.music.and.video.view.MusicJumpView
import java.io.File

class SearchHolder(
    itemView: View,
    val adapter: SearchAdapter,
    val album: AlbumInfoTable?
) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.rcv_item_search_music
        fun deleteDownloadSong(song: SongInfoTable) {
            if (!song.playFilePath.isNullOrBlank()) {
                val file = File(song.playFilePath)
                if (file.exists()) {
                    if (file.delete()) {
                        song.playFilePath = null
                        DbHelper.insetOrUpdateSong(song)
                    }
                } else {
                    song.playFilePath = null
                    DbHelper.insetOrUpdateSong(song)
                }
                //如果删除的是当前正在播放的歌曲，刷新状态
                if (song.dbId == AppData.currentPlaySong.data?.song?.dbId) {
                    AppData.currentPlaySong.data?.apply {
                        song.playFilePath = null
                        AppData.currentPlaySong.success(this)
                    }
                }
            }
        }
    }

    private val songName = itemView.findViewById<TextView>(R.id.songNameTv)
    private val songDes = itemView.findViewById<TextView>(R.id.songDesTv)
    private val songSource = itemView.findViewById<TextView>(R.id.songSourceTv)
    private val songCover = itemView.findViewById<ImageView>(R.id.songCoverIv)
    private val songCoverTv = itemView.findViewById<TextView>(R.id.songCoverTv)
    private val editEt = itemView.findViewById<ImageView>(R.id.editEt)
    private val musicJumpFl = itemView.findViewById<FrameLayout>(R.id.musicJumpFl)
    private val downloadedTagIv = itemView.findViewById<View>(R.id.downloadedTagIv)

    init {
        editEt.setOnClickListener {
            showPopup(it)
        }
    }


    fun setData(bean: SongInfoTable, keywords: String?, isNeedMusicJumpView: Boolean = false) {
        if (bean.playFilePath.isNullOrEmpty()) {
            downloadedTagIv.gone()
        } else {
            downloadedTagIv.visible()
        }
        //添加当前播放的跳动音符
        if (isNeedMusicJumpView
            && AppData.currentPlayIndex == adapterPosition
        ) {
            musicJumpFl.addView(
                MusicJumpView(itemView.context).apply {
                    pointerColor = getResColor(R.color.colorAccent)
                    pointerNum = 5
                    pointerWidth = 6f
                }, FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
                )
            )
        } else {
            musicJumpFl.removeAllViews()
        }
        if (keywords.isNullOrBlank()) {
            setUi(bean)
        } else {
            setUi(bean)
        }
    }

    private fun setUi(bean: SongInfoTable) {
        songName.textFrom(bean.name)
        when (bean.type) {
            //VIP
            DbCons.TYPE_VIP -> {
                songDes.textFromSpanBean(
                    mutableListOf(
                        SpanBean("[VIP] ", textColor = Color.RED),
                        SpanBean(bean.des)
                    )
                )
            }
            else -> {
                songDes.textFrom((bean.des))
            }
        }
//        songSource.textFrom("${getResString(R.string.song_source)}：${bean.type}")
        var coverPath = bean.coverUrlPath
        if (!bean.coverFilePath.isNullOrEmpty()) {
            coverPath = bean.coverFilePath
        }
//        songCover.setImageGlide(coverPath, R.drawable.default_cover_icon, 4)
        songCoverTv.textFrom("${adapterPosition + 1}")
    }


    /**
     * 显示Popup
     */
    private fun showPopup(it: View?) {
        var ctx = it?.context ?: return

        if (ctx !is AppCompatActivity) {
            if (ctx is ContextWrapper) {
                ctx = ctx.baseContext
            }
        }
        val song = adapter.datas[layoutPosition]
        var popupWindow: PopupWindow? = null
        val rootView = BaseLinearLayout(
            ctx
        ).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dip(16), 0, dip(16), 0)
            setBackgroundResource(R.drawable.bg_dialog_play_list_8_r)
            addView(BaseTextView(ctx).apply {
                text = "歌曲：${song.name}"
                setLines(1)
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.GRAY)
                textSize = 12f
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                text = "添加到歌单"
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    popupWindow?.dismiss()
                    SelectPlayListDialog.show(ctx.safeGet(), album) {
                        launchThread {
                            DbHelper.addSongToAlbum(song, it)
                            if (it.dbId == DbCons.ALBUM_ID_HEART) {
                                dbViewModel.queryHeartList()
                            }
                            "${song.name} 已经添加到 ${it.title}".toast(Toast.LENGTH_LONG)
                        }
                    }
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                text = "批量操作"
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    toAllSongPage(this, adapter.datas)
                    popupWindow?.dismiss()
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            if (song.playFilePath.isNullOrBlank() && ctx is AppCompatActivity) {
                addView(BaseTextView(ctx).apply {
                    text = "下载"
                    gravity = Gravity.CENTER_VERTICAL
                    setTextColor(Color.BLACK)
                    setOnClickListener {
                        popupWindow?.dismiss()
                        startDownload(ctx, song, album)
                    }
                }, LinearLayout.LayoutParams(dip(120), dip(40)))
            }
            if (album != null && album.id != DbCons.ALBUM_ID_CURRENT.toString() &&
                album.id != DbCons.ALBUM_ID_LOCAL.toString()
            ) {
                addView(BaseTextView(ctx).apply {
                    text = "删除"
                    gravity = Gravity.CENTER_VERTICAL
                    setTextColor(Color.BLACK)
                    setOnClickListener {
                        launchThread {
                            DbHelper.removeSongFromAlbum(song, album)
                            //删除本地下载
                            if (album.dbId == DbCons.ALBUM_ID_DOWNLOADED) {
                                deleteDownloadSong(song)
                            }
                            switchToUi {
                                if (album.dbId == DbCons.ALBUM_ID_HEART) {
                                    dbViewModel.queryHeartList()
                                }
                                if (album.dbId == DbCons.ALBUM_ID_RECENT) {
                                    dbViewModel.queryRecentPlayList()
                                }
                                if (album.dbId == DbCons.ALBUM_ID_DOWNLOADED) {
                                    dbViewModel.queryDownloadList()
                                }
                                adapter.datas.removeAt(layoutPosition)
                                adapter.notifyItemRemoved(layoutPosition)
                                popupWindow?.dismiss()
                            }
                        }
                    }
                }, LinearLayout.LayoutParams(dip(120), dip(40)))
            }
        }
        popupWindow = MvvmPopupWindow(
            rootView, ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            showAsDropTopOrBottom(it, true, dip(16))
        }
    }


    /**
     * 开始下载
     */
    private fun startDownload(ctx: Context, song: SongInfoTable, it: AlbumInfoTable?) {
        if (ctx is AppCompatActivity) {
//            DownloadSongHelper(ctx, SongModel(song), it?.dbId).startDown()
            DownSongWithNotification.startDown(
                ctx,
                SongModel(song), it?.dbId
            )
        }
    }

    /**
     * 跳转到批量操作界面
     */
    private fun toAllSongPage(itemView: View, datas: MutableList<SongInfoTable>) {
        var ctx = itemView.context
        if (ctx !is AppCompatActivity) {
            if (ctx is ContextThemeWrapper) {
                ctx = ctx.baseContext
            }
        }
        AddSongToAlbumActivity.start(
            ctx.safeGet<AppCompatActivity>(),
            datas,
            album
        )
    }
}