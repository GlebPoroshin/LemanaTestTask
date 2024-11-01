package com.gleb.lemana.task.presentation.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.SelectedLike: ImageVector
	get() {
		if (_SelectedLike != null) {
			return _SelectedLike!!
		}
		_SelectedLike = ImageVector.Builder(
            name = "SelectedLike",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
			path(
    			fill = SolidColor(Color(0xFF292526)),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(12f, 0f)
				horizontalLineTo(12f)
				arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 24f, 12f)
				verticalLineTo(12f)
				arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 24f)
				horizontalLineTo(12f)
				arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 12f)
				verticalLineTo(12f)
				arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 0f)
				close()
			}
			path(
    			fill = SolidColor(Color(0xFFFFFFFF)),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(14.96f, 6.06668f)
				curveTo(13.75330f, 6.06670f, 12.67330f, 6.65330f, 120f, 7.55340f)
				curveTo(11.32670f, 6.65330f, 10.24670f, 6.06670f, 9.040f, 6.06670f)
				curveTo(6.99330f, 6.06670f, 5.33330f, 7.73330f, 5.33330f, 9.79340f)
				curveTo(5.33330f, 10.58670f, 5.460f, 11.320f, 5.680f, 120f)
				curveTo(6.73330f, 15.33330f, 9.980f, 17.32670f, 11.58670f, 17.87330f)
				curveTo(11.81330f, 17.95330f, 12.18670f, 17.95330f, 12.41330f, 17.87330f)
				curveTo(14.020f, 17.32670f, 17.26670f, 15.33330f, 18.320f, 120f)
				curveTo(18.540f, 11.320f, 18.66670f, 10.58670f, 18.66670f, 9.79340f)
				curveTo(18.66670f, 7.73330f, 17.00670f, 6.06670f, 14.960f, 6.06670f)
				close()
			}
		}.build()
		return _SelectedLike!!
	}

private var _SelectedLike: ImageVector? = null
