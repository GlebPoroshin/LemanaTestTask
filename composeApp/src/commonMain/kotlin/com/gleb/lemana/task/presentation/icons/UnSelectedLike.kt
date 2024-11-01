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

val Icons.UnSelectedLike: ImageVector
	get() {
		if (_UnSelectedLike != null) {
			return _UnSelectedLike!!
		}
		_UnSelectedLike = ImageVector.Builder(
            name = "UnSelectedLike",
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
    			fill = SolidColor(Color(0xFFFDFDFD)),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(12f, 18.4333f)
				curveTo(11.79330f, 18.43330f, 11.59330f, 18.40670f, 11.42670f, 18.34670f)
				curveTo(8.880f, 17.47330f, 4.83330f, 14.37330f, 4.83330f, 9.79340f)
				curveTo(4.83330f, 7.460f, 6.720f, 5.56670f, 9.040f, 5.56670f)
				curveTo(10.16670f, 5.56670f, 11.220f, 6.00670f, 120f, 6.79340f)
				curveTo(12.780f, 6.00670f, 13.83330f, 5.56670f, 14.960f, 5.56670f)
				curveTo(17.280f, 5.56670f, 19.16670f, 7.46670f, 19.16670f, 9.79340f)
				curveTo(19.16670f, 14.380f, 15.120f, 17.47330f, 12.57330f, 18.34670f)
				curveTo(12.40670f, 18.40670f, 12.20670f, 18.43330f, 120f, 18.43330f)
				close()
				moveTo(9.04001f, 6.56668f)
				curveTo(7.27330f, 6.56670f, 5.83330f, 8.01340f, 5.83330f, 9.79340f)
				curveTo(5.83330f, 14.34670f, 10.21330f, 16.880f, 11.75330f, 17.40670f)
				curveTo(11.87330f, 17.44670f, 12.13330f, 17.44670f, 12.25330f, 17.40670f)
				curveTo(13.78670f, 16.880f, 18.17330f, 14.35330f, 18.17330f, 9.79340f)
				curveTo(18.17330f, 8.01340f, 16.73330f, 6.56670f, 14.96670f, 6.56670f)
				curveTo(13.95330f, 6.56670f, 13.01330f, 7.040f, 12.40670f, 7.860f)
				curveTo(12.220f, 8.11340f, 11.79330f, 8.11340f, 11.60670f, 7.860f)
				curveTo(10.98670f, 7.03340f, 10.05330f, 6.56670f, 9.040f, 6.56670f)
				close()
			}
		}.build()
		return _UnSelectedLike!!
	}

private var _UnSelectedLike: ImageVector? = null
