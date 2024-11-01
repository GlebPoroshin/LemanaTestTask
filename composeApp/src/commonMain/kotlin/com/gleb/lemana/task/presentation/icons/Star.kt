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

val Icons.Star: ImageVector
	get() {
		if (_Star != null) {
			return _Star!!
		}
		_Star = ImageVector.Builder(
            name = "Star",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 18f,
            viewportHeight = 18f
        ).apply {
			path(
    			fill = SolidColor(Color(0xFFFFD33C)),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(15.9627f, 6.20685f)
				lineTo(11.4996f, 5.55822f)
				lineTo(9.50449f, 1.51349f)
				curveTo(9.450f, 1.40270f, 9.36040f, 1.31310f, 9.24960f, 1.25860f)
				curveTo(8.97190f, 1.12150f, 8.63440f, 1.23570f, 8.49550f, 1.51350f)
				lineTo(6.50039f, 5.55822f)
				lineTo(2.0373f, 6.20685f)
				curveTo(1.91430f, 6.22440f, 1.80180f, 6.28240f, 1.71560f, 6.37030f)
				curveTo(1.61150f, 6.47740f, 1.55410f, 6.62130f, 1.55610f, 6.77060f)
				curveTo(1.55810f, 6.920f, 1.61920f, 7.06240f, 1.72620f, 7.16660f)
				lineTo(4.95527f, 10.3149f)
				lineTo(4.19238f, 14.7604f)
				curveTo(4.17450f, 14.86380f, 4.18590f, 14.97010f, 4.22540f, 15.06740f)
				curveTo(4.26490f, 15.16460f, 4.33080f, 15.24880f, 4.41570f, 15.31050f)
				curveTo(4.50070f, 15.37220f, 4.60120f, 15.40880f, 4.70580f, 15.41630f)
				curveTo(4.81050f, 15.42380f, 4.91520f, 15.40170f, 5.0080f, 15.35270f)
				lineTo(9f, 13.2539f)
				lineTo(12.992f, 15.3527f)
				curveTo(13.1010f, 15.41080f, 13.22750f, 15.43010f, 13.34880f, 15.4090f)
				curveTo(13.65470f, 15.35630f, 13.86040f, 15.06620f, 13.80760f, 14.76040f)
				lineTo(13.0447f, 10.3149f)
				lineTo(16.2738f, 7.16661f)
				curveTo(16.36170f, 7.08050f, 16.41970f, 6.9680f, 16.43730f, 6.84490f)
				curveTo(16.48480f, 6.53730f, 16.27030f, 6.25260f, 15.96270f, 6.20690f)
				close()
			}
		}.build()
		return _Star!!
	}

private var _Star: ImageVector? = null
