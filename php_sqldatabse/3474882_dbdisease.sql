-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb24.awardspace.net
-- Generation Time: Jun 22, 2020 at 04:47 PM
-- Server version: 5.7.20-log
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `3474882_dbdisease`
--

-- --------------------------------------------------------

--
-- Table structure for table `chapter`
--

CREATE TABLE `chapter` (
  `ChapterID` int(11) NOT NULL,
  `ChapterName` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chapter`
--

INSERT INTO `chapter` (`ChapterID`, `ChapterName`) VALUES
(1, 'Bệnh nhiễm trùng và ký sinh trùng'),
(2, 'U (U tân sinh)'),
(3, 'Bệnh của máu, cơ quan tạo máu và các rối loạn liên quan đến cơ chế miễn dịch'),
(4, 'Bệnh nội tiết, dinh dưỡng và chuyển hóa'),
(5, 'Rối loạn tâm thần và hành vi'),
(6, 'Bệnh hệ thần kinh'),
(7, 'Bệnh mắt và phần phụ'),
(8, 'Bệnh của tai và xương chũm'),
(9, 'Bệnh hệ tuần hoàn'),
(10, 'Bệnh Hô hấp'),
(11, 'Bệnh hệ tiêu hoá'),
(12, 'Nhiễm trùng da và tổ chức dưới da'),
(13, 'Bệnh của hệ cơ – xương khớp và mô liên kết'),
(14, 'Bệnh hệ sinh dục – Tiết niệu'),
(15, 'Bệnh truyền nhiễm');

-- --------------------------------------------------------

--
-- Table structure for table `disease`
--

CREATE TABLE `disease` (
  `DiseaseID` int(11) NOT NULL,
  `DiseaseName` varchar(200) NOT NULL,
  `Symptom` varchar(500) NOT NULL,
  `Advice` varchar(10000) NOT NULL,
  `Illustration` varchar(200) NOT NULL,
  `GroupID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `disease`
--

INSERT INTO `disease` (`DiseaseID`, `DiseaseName`, `Symptom`, `Advice`, `Illustration`, `GroupID`) VALUES
(1, 'Viêm gan A cấp', 'Một số người không biểu hiện bất kỳ triệu chứng nào khi mắc bệnh. Nếu có triệu chứng, thường khoảng sau 2 đến 6 tuần sau khi virus xâm nhập vào cơ thể, người bệnh sẽ có các biểu hiện:Vàng da, Vàng tròng trắng mắt, Phân nhạt màu thường có màu xám, Nước tiểu màu nâu sẫm, Đau bụng, Ngứa ngáy toàn thân, Sốt nhẹ, Mệt mỏi, Biếng ăn, Buồn nôn, nôn mửa', 'Đối với bệnh viêm gan A điều trị chỉ giúp nâng đỡ thể trạng và giải quyết các triệu chứng. Không có phương pháp đặc hiệu để điều trị viêm gan A. Cơ thể người bệnh sẽ tự đào thải virus viêm gan A sau vài tuần mà không cần chữa trị. Bệnh nhân có thể được chăm sóc ở nhà mà không cần phải nhập viện. Đối với bệnh viêm gan A: Đây là một căn bệnh ngắn hạn vì thế cách điều trị sẽ đơn giản hơn. Nếu bạn có triệu chứng nôn mửa hoặc tiêu chảy, hãy đến phòng khám để được kê toa và dùng thuốc theo chỉ định của bác sĩ. Tiêm ngừa viêm gan A có thể giúp ngăn ngừa bệnh.', 'https://vinmec-prod.s3.amazonaws.com/images/20190520_091806_769110_20161124102807-1053.max-1800x1800.jpg', 1),
(2, 'Lao hô hấp, có xác nhận về vi khuẩn học và mô học', 'Có thể gây nên các triệu chứng toàn thân như: Sốt (thường là sốt nhẹ về chiều hoặc đêm), kèm theo là mệt mỏi, kém ăn, gầy sút cân, da xanh, thiếu máu', 'Chỉ định dùng kháng sinh: Theo hướng dẫn của GOLD 2013, khuyến cáo chỉ\r\nsử dụng kháng sinh trong các trường hợp sau:\r\n+ Người bệnh typ I (Bằng chứng B).\r\n+ Người bệnh typ II (Bằng chứng C).\r\n+ Người bệnh cần thông khí nhân tạo (xâm nhập hoặc không xâm nhập) (Bằng\r\nchứng B).\r\n- Thời gian điều trị kháng sinh: Thời gian dùng kháng sinh 5 - 10 ngày (Bằng\r\nchứng D).\r\n- Lựa chọn kháng sinh theo mức độ nặng của đợt cấp BPTNMT và cân nhắc trên\r\ntính kháng của vi khuẩn tại địa phương. Đường dùng của kháng sinh (uống hoặc tĩnh\r\nmạch) tùy vào tình trạng người bệnh có uống được không và dược động học của kháng\r\nsinh', 'https://vinmec-prod.s3.amazonaws.com/images/20190613_031510_515218_benh-lao.max-800x800.jpg', 2),
(3, 'Bệnh sán lá gan Opisthorchis', 'Có triệu chứng đau tức vùng gan, rối loạn tiêu hóa (kém ăn, bụng ậm ạch khó tiêu); đôi khi có biểu hiện sạm da, vàng da và dấu hiệu gan to hay xơ gan tùy theo mức độ của bệnh', '- Điều trị sớm, đủ liều và dùng thuốc đặc hiệu.\r\n- Điều trị hỗ trợ khi cần thiết để nâng cao thể trạng cho bệnh nhân.\r\n- Lưu ý những trường hợp chống chỉ định điều trị cho phụ nữ có thai, những người đang bị bệnh cấp tính hoặc suy tim, suy gan, suy thận, bệnh tâm thần..., cơ địa dị ứng với thuốc cần dùng', 'https://vinmec-prod.s3.amazonaws.com/images/20200305_180556_282217_sanlagan.max-800x800.jpg', 3),
(5, 'Bệnh tả', 'Các triệu chứng chính của bệnh là tiêu chảy mất nước và ói mửa.', 'Biện pháp chữa trị quan trọng nhất là phải cung cấp lại đầy đủ nước, đường và muối, thường phải được tiêm vào mạch máu để không phải qua đường ruột. Ở các nước Thế giới thứ ba, người ta cũng chữa trị thành công và đơn giản bằng cách cho uống nước thay thế', 'https://aqualife.vn/wp-content/uploads/Nguy%C3%AAn-nh%C3%A2n-v%C3%A0-c%C3%A1ch-ph%C3%B2ng-tr%C3%A1nh-b%E1%BB%87nh-t%E1%BA%A3-1170x630.jpg', 4),
(6, '\r\nSuy dinh dưỡng nặng do thiếu protein - năng lượng, không đặc hiệu', 'Trẻ bị suy dinh dưỡng do thiếu protein năng lượng sẽ có biểu hiện chậm phát triển về thể lực và trí tuệ, ảnh hưởng đến khả năng học tập và lao động sau này. Bệnh có thể xảy ra ở bất cứ độ tuổi nào, tuy nhiên, có thể phòng ngừa được nếu có chế độ ăn đầy đủ dinh dưỡng và hợp lý', 'Điều trị các bệnh lý đi kèm, thay đổi chế độ ăn phù hợp, bổ sung các vi khoáng chất và vitamin.', 'https://vinmec-prod.s3.amazonaws.com/images/20190901_172332_383812_suy-dinh-duong-prot.max-1800x1800.jpg', 14),
(7, 'Bệnh viên đường hô hấp cấp - do virut corona', 'Sốt, ho khan, mệt mỏi(các triệu chứng thường gặp), đau nhức, đau họng, tiêu chảy, viêm kết mạc, đau đầu, mất vị giác hoặc khứu giác, da nổi mẩn hay ngón tay hoặc ngón chân bị tấy đỏ hoặc tím tái(các triệu chứng ít gặp hơn), khó thở, đau hoặc tức ngực, mất khả năng nói hoặc cử động (các triệu chứng nghiêm trọng)', 'Cho đến nay, chưa có một loại thuốc nào được khuyến nghị sử dụng trong việc phòng ngừa và điều trị vi rút Corona chủng mới. Tuy nhiên, những người nhiễm COVID-19 sẽ được điều trị triệu chứng và những trường hợp nặng sẽ được điều trị hỗ trợ tối ưu. Một số phương pháp điều trị đặc hiệu hiện đang được nghiên cứu và sẽ được thử nghiệm trên lâm sàng. ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTTsJsE-Rr_tGgt0ZBo9QeHtqILbA5JWB-vJ3QDCQd_bcd3iOEy&usqp=CAU', 45),
(8, 'Hội chứng thiếu iod bẩm sinh', 'Cân nặng lúc đẻ to hơn bình thường, giảm vận động, ngủ nhiều, vàng da sơ sinh kéo dài trên 2 tuần, da khô và không tìm thấy nguyên nhân bệnh lý về gan, thân nhiệt dưới 35ºC, da lạnh, nổi vân tím, bụng thường to, táo bón và có thể thoát vị rốn kèm theo, thóp trước lớn', 'Suy giáp bẩm sinh không thể chữa khỏi hoàn toàn mà trẻ bắt buộc phải dùng hormone thay thế trong suốt cuộc đời Tuy nhiên nếu trẻ được phát hiện sớm, điều trị đều đặn, khám đúng hẹn trẻ hoàn toàn phát triển bình thường cả về thể chất và tinh thần. Liệu pháp hormone thay thế là phương pháp điều trị suy giáp bẩm sinh duy nhất đem lại hiệu quả và an toàn. Còn trường hợp phát hiện ở giai đoạn muộn dùng hormone thay thế có thể phần nào cải thiện được sự phát triển của não bộ và sự phát triển về thể chất. Phát hiện càng sớm trẻ được dùng thuốc sớm thì giảm thiểu được các biến chứng của thiếu hormone tuyến giáp.', 'https://ichgiapvuong.vn/data/upload/image/2015/08/15/tre-bi-suy-giap-bam-sinh.jpg', 16),
(9, 'Chứng Bạch tạng', 'Dạng bạch tạng dễ nhận biết nhất là tóc trắng và da rất trắng hồng so với anh chị em của người bệnh. Màu da (sắc tố) và màu tóc có thể từ thay đổi từ trắng đến nâu, và thậm chí, có thể gần giống như màu của bố mẹ hoặc anh chị em không bị bạch tạng.', 'Do bệnh bạch tạng là rối loạn di truyền do đó không thể được chữa khỏi. Điều trị tập trung vào việc chăm sóc mắt đúng cách và theo dõi da để phát hiện sớm các dấu hiệu bất thường', 'src="https://vinmec-prod.s3.amazonaws.com/images/20190530_070015_875229_bach-tang_2.max-800x800.jpg"', 17),
(10, 'Tâm thần phân liệt', ' Mất đi ý muốn làm việc, giảm sự biểu lộ tình cảm,  sự cách ly xã hội, không nhận thức được rằng bản thân mình đang bị bệnh', 'Hiện nay phương pháp điều trị hiệu quả nhất bệnh TTPL là sự phối hợp giữa thuốc chống loạn thần và công tác phục hồi chức năng tâm lý xã hội cho bệnh nhân. Thuốc chống loạn thần. Các thuốc chống loạn thần cổ điển như như Aminazine , Haldol …… đã giúp ích rất nhiều trong việc điều trị bệnh TTPL. Nhờ các thuốc này, ngày nay đa số bệnh nhân TTPL không cần nằm bệnh viện tâm thần lâu dài mà có thể điều trị ngoại trú bằng cách đến lãnh thuốc đều đặn tại các Phòng khám tâm thần quận huyện hay các Trạm y tế phường xã. Họ vừa uống thuốc vừa có thể sống thoải mái trong gia đình và xã hội. Hiện nay đã xuất hiện các thuốc chống loạn thần thuộc thế hệ mới như risperidone, olanzapine … vừa có hiệu quả hơn mà lại ít tác dụng phụ hơn các thuốc loại cổ điển nên đã góp phần làm bệnh nhân yên tâm điều trị lâu dài. ', 'https://vinmec-prod.s3.amazonaws.com/images/20191112_142217_073967_tam-than-phan-liet.max-800x800.jpg', 20),
(11, 'Đục thủy tinh thể người già', 'Nhìn sự vật bị mờ sương, thấy màu sắc nhạt nhòa, khó phân biệt, chói mắt, lóa mắt khi nhìn ánh sáng, nhìn thấy chấm đen ruồi bay trước mắt, thấy hào quang xung quanh bóng đèn, khó nhìn vào ban đêm, khó đọc chữ trên sách báo, cần nhiều ánh sáng hơn để nhìn, nhìn 1 nhòe thành 2,3 hình, mắt tăng độ kính nhanh', 'Với những trường hợp đục thủy tinh thể giai đoạn đầu chưa cần thiết phải phẫu thuật, các bác sĩ sẽ cho bổ sung một số vitamin như C, A, E… và một số hoạt chất khác để làm chậm lại tiến trình đục thủy tinh thể. Tăng cường ánh sáng trong nhà, tránh tiếp xúc trực tiếp với ánh sáng mặt trời, khói bụi. Hiện nay phương pháp điều trị đục thủy tinh thể hiệu quả nhất vẫn là phẫu thuật. Phương pháp phẫu thuật đã tồn tại gần hai thế kỷ, nhưng đặt kính nội nhãn (thể thuỷ tinh nhân tạo) là phương pháp mới, được đề xướng từ năm 1949. ', 'https://www.matsaigon.com/wp-content/uploads/2017/10/duc-thuy-tinh-the-2.jpg', 27),
(12, 'Viêm kết mạc (đau mắt đỏ)', 'Đỏ hoặc sưng lòng trắng của mắt hoặc bên trong mí mắt, mắt bị viêm kết mạc thường tăng lượng nước mắt, xuất hiện ghèn mắt màu vàng, trắng hoặc xanh, ngứa, nóng mắt, khó chịu khi đeo kính áp tròng', ' Lau rửa ghèn, dử mắt ít nhất 2 lần/ngày bằng khăn giấy ẩm hoặc bông. Lau xong vứt bỏ khăn, không sử dụng lại.\r\n\r\n– Người bệnh cần được nghỉ ngơi, cách ly, dùng thuốc theo đơn của thầy thuốc.\r\n\r\nKhông tra vào mắt lành thuốc nhỏ của mắt đang bị nhiễm khuẩn. Không dùng chung thuốc tra mắt vì có thể mỗi người nhiễm một vi khuẩn, virus khác nhau và các đầu lọ thuốc đã nhiễm khuẩn. Dùng chung thuốc thì vô tình sẽ có thể tiếp tục nhiễm loại vi khuẩn, virus khác khiến bệnh nặng thêm hoặc bệnh tái lại. Nên rửa tay sạch trước khi vệ sinh mắt, nhỏ mắt. Sau khi vệ sinh mắt và nhỏ thuốc mắt, cần tiếp tục rửa tay lần nữa bằng xà phòng hoặc dung dịch sát khuẩn. Trẻ em bị bệnh nên nghỉ học, không đưa trẻ đến trường hoặc những nơi đông người để tránh lây bệnh cho người khác.', 'https://login.medlatec.vn//ImagePath/images/20200420/20200420_benh-viem-ket-mac-duoc-biet-den-voi-ten-goi-la-dau-mat-do-.jpg', 30),
(13, 'Viêm tai ngoài', 'Sưng nề ống tai, đỏ da ống tai, nóng, đau hoặc cảm giác khó chịu trong tai. Ngứa tai, chảy dịch, chảy mủ tai, ù tai hoặc nghe kém. Đau nhiều vùng mặt, đầu hay cổ có thể là dấu hiệu cho thấy nhiễm trùng đã tiến triển đáng kể. Triệu chứng đi kèm như sốt hay sưng đau các hạch có thể chỉ điểm nhiễm trùng nặng hơn', 'Nếu không tự khỏi thì bệnh thường có thể điều trị tốt bằng thuốc kháng sinh nhỏ tai. Bác sĩ cũng có thể kê toa thuốc nhỏ tai chứa kháng sinh và kháng viêm để làm giảm sưng nề ống tai. Các thuốc nhỏ tai này thường được sử dụng vài lần trong ngày, từ 7 đến 10 ngày. Nếu viêm ống tai ngoài gây ra do nấm, bác sĩ sẽ kê đơn thuốc kháng nấm nhỏ tai. Viêm tai ngoài do nấm thường xảy ra ở người đái tháo đường hay người bị suy giảm miễn dịch. Để giảm triệu chứng của bệnh, cần phải giữ cho tai khô ráo. Khi đó nhiễm trùng có thể lành nhanh hơn. Thuốc giảm đau tự mua được như ibuprofen hay acetaminophen có thể giảm đau tai. Trong trường hợp đau dữ dội, mới cần đến những thuốc giảm đau kê đơn.', 'https://vinmec-prod.s3.amazonaws.com/images/20191026_020611_793024_viem-tai-trong.max-1800x1800.jpg', 31),
(14, 'Nhồi máu cơ tim cấp', 'Đau thắt ngực: được miêu tả giống như cảm giác có vật gì đó đè nén, chèn ép ở vùng ngực trước tim hoặc phía sau xương ức. Đau có thể lan tới cổ, hàm, vai, cánh tay, lưng. Cơn đau thường kéo dài và không đỡ khi dùng thuốc, khó thở, hụt hơi, thở gấp, buồn nôn hoặc nôn, đổ mồ hôi lạnh, ngất xỉu, choáng váng, lú lẫn, buồn đi cầu', 'Các biện pháp điều trị bệnh cần được tiến hành càng sớm càng tốt để làm giảm mức độ tổn thương của cơ tim. Cơ tim bị hoại tử vì không được cung cấp oxy do tắc nghẽn động mạch vành, bởi vậy có thể làm giảm kích thước vùng nhồi máu cơ tim theo hai cách, đó là: làm tan cục máu đông để khôi phục lưu lượng máu tới nuôi tim và làm giảm nhu cầu tiêu thụ oxy của cơ tim. Sử dụng thuốc. Can thiệp nong mạch vành qua da. Phẫu thuật bắc cầu động mạch vành', 'https://suytim.com.vn/upload/2019/05/24/origin_editor/Tac-nghen-dong-mach-vanh-do-cuc-mau-dong-la-nguyen-nhan-chinh-gay-nhoi-mau-co-tim-cap.jpg', 34),
(15, 'Bệnh Tăng huyết áp vô căn (nguyên phát)', 'Chóng mặt. Chảy máu cam. Đau đầu. Tức ngực. Tiểu ra máu. Thay đổi thị giác', 'Nếu gặp những triệu chứng bất thường trên nghi tăng huyết áp, bạn cần đến cơ sở y tế gần nhất, để xác định chính xác tình trạng sức khỏe và tim mạch, huyết áp', 'https://vinmec-prod.s3.amazonaws.com/images/20190828_022624_019537_cao_huyet_ap_2.max-800x800.jpg', 35),
(16, 'Viêm phổi do adenovirus', 'Thời kỳ nung bệnh từ 6 - 8 ngày. Bệnh đột ngột với sốt cao 390C, ho, chảy nước mũi và các dấu hiệu tổn thương thực thể ở phổi, các tổn thương này lan rộng và kéo dài. Bệnh viêm phổi cấp do Adenovirus typ 3,4,7 và 14 gây ra và thường xuyên xảy ra trong các tập thể thanh thiếu niên, đặc biệt typ7 thường xảy ra ở viêm phổi nặng, chủ yếu ở trẻ em, tỷ lệ tử vong cao, khi khỏi để lại di chứng ở phổi', 'Chưa có thuốc điều trị đặc hiệu. Cần đề phòng bội nhiễm vi khuẩn đặc biệt ở trẻ suy dinh dưỡng. Tuy nhiên, các biến chứng do nhiễm khuẩn thứ phát rất hiếm', 'https://kenhitv.vn/wp-content/uploads/2019/11/viem-phoi-virus.jpg', 37),
(17, 'Bệnh Pemphigus', 'Bọng nước xuất hiện đầu tiên ở một chỗ nào đó trên da hay trên niêm mạc mồm,hầu họng. Bọng nước có thể nhỏ bằng hạt đạu xanh, quả táo, quả trứng gà , bọng nước và phỏng nước tính chất là nhẽo ( không căng), dễ vỡ, vỡ nước, phỏng nước mọc trên da lành, về sau chợt rộng dễ chảy máu, vẩy tiết', 'Corticoid liều đầu 300 - 500 mg/ ngày, sau đó giảm liều rất từ từ xuống và giảm dần xuống đến lúc nào không có tổn thương mới thì gọi là liều duy trì. Duy trì cho đến khi nào hết tổn thương ở trên da và niêm mạc. Dùng kéo dài phải chú ý biến chứng loét dạ dày , hội chứng cushing trứng cá. Hiện nay còn dùng methotrexat có nhiều tiến triển tốt, một tuần uống một lần 15 - 20 mg cho 15 ngày, đề phòng hạ BC, HC. Tổn thương của gan thận, có thể gây quái thai (cấm chửa đẻ sau 1 năm uống).', 'https://nhathuoclongchau.com/upload/images/benh/519/2018/05/519-da-bong-nuoc-tu-mien-pemphigus-3981-5b02.jpg', 39),
(18, 'Mề đay (mày đay)', 'Ngứa trên da, ngứa về đêm. Xuất hiện các nốt sần ở một ví trí hoặc toàn thân. Phù ở lưỡi, suy hô hấp (đối với những người bị nổi mề đay ở đường hô hấp). Tình trạng buồn nôn, chóng mặt, đau bụng cũng xuất hiện kèm theo', 'Khi bị mề đay trong thời gian dài những không có dấu hiệu khỏi, bệnh tình chuyển biến nặng, bệnh nhân nên nhanh chóng đến các cơ sở y tế uy tín để được điều kiểm tra và điều trị tránh những biến chứng phức tạp', 'https://thoaihoacotsong.vn/wp-content/uploads/Hinh-anh-noi-me-day-o-benh-nhan.jpg', 41),
(19, 'Bệnh loãng xương', 'Đau nhức đầu xương, người bệnh sẽ cảm thấy mỏi dọc các xương dài, thậm chí đau nhức như bị kim chích toàn thân. Giảm mật độ xương khiến xương ở cột sống có thể bị xẹp (còn gọi là gãy lún). Biểu hiện của tình trạng này bao gồm có cơn đau lưng cấp, giảm chiều cao, dáng đi khom và gù lưng. Sụt cân, vã mồ hôi, buồn bã chân tay, chuột rút, các khớp thường xuyên có dấu hiệu kêu lục cục. Đau ở cột sống, thắt lưng hoặc hai bên liên sườn, gây ảnh hưởng đến các dây thần kinh liên sườn, dây thần kinh đùi', 'Cung cấp lượng canxi cho cơ thể đúng theo mức khuyến cáo, không cung cấp dư thừa. Bổ sung 600 đơn vị quốc tế (IU) vitamin D mỗi ngày cho người ở độ tuổi từ 1 đến 70 tuổi và 800 IU mỗi ngày từ 71 tuổi trở lên. Duy trì trọng lượng tiêu chuẩn, không thừa cân cũng không thiếu cân. Thực hiện các bài tập như đi bộ, chạy bộ, nhảy múa và thể dục nhịp điệu 3-4 giờ mỗi tuần. Ngừng hút thuốc. Hạn chế thức uống có cồn, cà phê và nước giải khát có ga. Trao đổi với bác sĩ về các loại thuốc đang sử dụng có nguy cơ gây giảm mật độ xương. Trao đổi với bác sĩ về lợi ích và rủi ro của liệu pháp thay thế estrogen nếu người bệnh đang trong thời kỳ mãn kinh hoặc đã cắt bỏ buồng trứng. Khi phát hiện loãng xương, ngoài chế độ ăn uống, sinh hoạt hợp lý thì người bệnh cần phải bổ sung các loại thuốc điều trị loãng xương. Tùy theo từng trường hợp cụ thể người bệnh sẽ được tư vấn sử dụng những loại thuốc chống loãng xương phù hợp. Thay đổi lối sống: ngừng hút thuốc lá, hạn chế uống rượu. Ngăn ngừa gãy xương bằng cách giảm thiểu các nguy cơ té ngã. Tập thể dục là phần quan trọng trong quá trình điều trị loãng xương. Tập thể dục không chỉ giúp xương khỏe mạnh, mà còn làm tăng sức mạnh cơ bắp, sự phối hợp và cân bằng cơ thể, từ đó giúp sức khỏe tốt hơn. Mặc dù tập thể dục tốt cho người bị loãng xương, nhưng cũng phải cẩn thận, tránh vận động quá mạnh vì có thể dẫn đến gãy xương', 'https://suckhoekhop.cdn.vccloud.vn/wp-content/uploads/2019/12/lo%C3%A3ng-x%C6%B0%C6%A1ng-2.jpg', 42),
(20, 'Viêm cầu thận cấp', 'Sưng húp ở mặt (phù nề). Đi tiểu ít hơn bình thường. Máu trong nước tiểu (nước tiểu tối màu, rỉ sét). Lượng nước dư thừa trong phổi, gây ho. Huyết áp cao.', 'Một số trường hợp viêm cầu thận cấp tính, đặc biệt ở những người sau khi bị nhiễm Strep, thường có xu hướng tự cải thiện và không cần điều trị cụ thể nào. Nói chung, mục tiêu của điều trị viêm cầu thận cấp là để bảo vệ thận khỏi bị tổn thương thêm. Giữ huyết áp dưới sự kiểm soát chính là chìa khóa quan trọng để bảo vệ thận. Để kiểm soát huyết áp cao và làm chậm quá trình suy giảm chức năng thận, bác sĩ có thể kê toa một trong vài loại thuốc, bao gồm thuốc lợi tiểu, thuốc ức chế men chuyển đổi angiotensin (ACE), thuốc chặn thụ thể angiotensin II. Đối với viêm cầu thận cấp tính và suy thận cấp tính, thẩm tách máu có thể giúp loại bỏ chất lỏng dư thừa và kiểm soát huyết áp cao. Các phương pháp điều trị viêm cầu thận cấp lâu dài dành cho bệnh thận giai đoạn cuối là chạy thận và ghép thận. Khi không thể ghép thận, thường do sức khỏe yếu, chạy thận là lựa chọn duy nhất', 'https://chicucthuyhcm.org.vn/wp-content/uploads/2020/04/viem-cau-than.jpg', 43);

-- --------------------------------------------------------

--
-- Table structure for table `disease_user`
--

CREATE TABLE `disease_user` (
  `UserID` int(11) NOT NULL,
  `DiseaseID` int(11) NOT NULL,
  `Saved` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `disease_user`
--

INSERT INTO `disease_user` (`UserID`, `DiseaseID`, `Saved`) VALUES
(7, 6, 0),
(7, 5, 0),
(7, 7, 1),
(7, 2, 0),
(7, 8, 0),
(7, 9, 0),
(7, 14, 0),
(7, 1, 0),
(7, 13, 1),
(7, 3, 0),
(1, 5, 0),
(7, 12, 1),
(7, 17, 1),
(1, 13, 1),
(18, 1, 1),
(18, 2, 0),
(19, 11, 0),
(19, 9, 0),
(20, 14, 0),
(20, 14, 0),
(20, 15, 0),
(20, 16, 0),
(20, 18, 0),
(20, 20, 0);

-- --------------------------------------------------------

--
-- Table structure for table `group`
--

CREATE TABLE `group` (
  `GroupID` int(11) NOT NULL,
  `GroupName` varchar(200) NOT NULL,
  `ChapterID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `group`
--

INSERT INTO `group` (`GroupID`, `GroupName`, `ChapterID`) VALUES
(1, 'Viêm gan virus', 1),
(2, 'Bệnh lao', 1),
(3, 'Bệnh giun sán', 1),
(4, 'Bệnh nhiễm trùng đường ruột', 1),
(5, 'Bệnh nhiễm trùng lây truyền chủ yếu qua đường tình dục', 1),
(6, 'U lành', 2),
(7, 'U tân sinh không chắc chắn hoặc không biết tính chất', 2),
(8, 'U tân sinh tại chỗ', 2),
(9, 'U ác tính', 2),
(10, 'Bệnh thiếu máu dinh dưỡng', 3),
(11, 'Các rối loạn đông máu, ban xuất huyết và tình trạng xuất huyết khác', 3),
(12, 'Các rối loạn liên quan đến cơ chế miễn dịch', 3),
(13, 'Suy tuỷ xương và các bệnh thiếu máu khác', 3),
(14, 'Suy dinh dưỡng', 4),
(15, 'Các rối loạn khác về sự điều hoà glucose và bài tiết của tụy nội tiết', 4),
(16, 'Bệnh tuyến giáp', 4),
(17, 'Rối loạn chuyển hoá', 4),
(18, 'Rối loạn khí sắc [cảm xúc]', 5),
(19, 'Các rối loạn bệnh tâm căn có liên quan đến stress và rối loạn dạng cơ thể', 5),
(20, 'Tâm thần phân liệt, rối loạn loại phân liệt và các rối loạn hoang tưởng', 5),
(21, 'Rối loạn tâm thần và hành vi do sử dụng chất tác động tâm thần', 5),
(22, 'Các rối loạn về phát triển tâm lý', 5),
(23, 'Bệnh mất myelin của hệ thần kinh trung ướng', 6),
(24, 'Bệnh chu kỳ và kịch phát', 6),
(25, 'Bệnh dây, rễ và đám rối thần kinh', 6),
(26, 'Bệnh thoái hoá khác của hệ thần kinh', 6),
(27, 'Bệnh thủy tinh thể', 7),
(28, 'Rối loạn thị giác và mù lòa', 7),
(29, 'Bệnh dịch kính và nhãn cầu', 7),
(30, 'Bệnh của kết mạc', 7),
(31, 'Bệnh của tai ngoài', 8),
(32, 'Bệnh của tai giữa và xương chũm', 8),
(33, 'Thể bệnh tim khác', 9),
(34, 'Bệnh tim thiếu máu cục bộ', 9),
(35, 'Bệnh lý tăng huyết áp', 9),
(36, 'Nhiễm trùng đường hô hấp dưới cấp khác', 10),
(37, 'Cúm và viêm phổi', 10),
(38, 'Nhiễm khuẩn da và mô dưới da', 12),
(39, 'Bệnh da bọng nước', 12),
(40, 'Viêm da và chàm', 12),
(41, 'Mày đay và hồng ban', 12),
(42, 'Bệnh của xương và sụn', 13),
(43, 'Bệnh cầu thận', 14),
(44, 'Sỏi tiết niệu', 14),
(45, 'Bệnh truyền nhiễm nhóm A', 15),
(46, 'Bệnh truyền nhiễm nhóm B', 15),
(47, 'Bệnh truyền nhiễm nhóm C', 15);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Job` varchar(200) NOT NULL,
  `TypeUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `FullName`, `UserName`, `Password`, `Phone`, `Job`, `TypeUser`) VALUES
(1, 'administrator', 'admin', '123456', '0123456789', 'admin', 0),
(7, 'Phạm Hoài Thanh', 'hoaithanh', '12345', '0329430682', 'IT', 0),
(8, '', 'thanh', 'thanh', '', '', 0),
(9, '', 'thanh2', '123456', '', '', 0),
(11, '', 'fadsfaaa', '123456', '', '', 0),
(12, '', 'jjjjjjjjjjj', '1234567', '', '', 0),
(13, '', 'hoai', 'thanh', '', '', 0),
(14, '', 'pin', 'pin', '', '', 0),
(15, '', 'pin13', '123', '', '', 0),
(17, '', 'họa', 'hoa', '', '', 0),
(18, 'nhi', 'nhi', 'nhi', '064', 'hg', 0),
(19, '', 'zxcv', 'zxcv', '', '', 0),
(20, 'IThades', 'bai', '123456', '0328430682', 'IT', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chapter`
--
ALTER TABLE `chapter`
  ADD PRIMARY KEY (`ChapterID`);

--
-- Indexes for table `disease`
--
ALTER TABLE `disease`
  ADD PRIMARY KEY (`DiseaseID`),
  ADD KEY `GroupID` (`GroupID`);

--
-- Indexes for table `disease_user`
--
ALTER TABLE `disease_user`
  ADD KEY `UserID` (`UserID`),
  ADD KEY `DiseaseID` (`DiseaseID`);

--
-- Indexes for table `group`
--
ALTER TABLE `group`
  ADD PRIMARY KEY (`GroupID`),
  ADD KEY `ChapterID` (`ChapterID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chapter`
--
ALTER TABLE `chapter`
  MODIFY `ChapterID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `disease`
--
ALTER TABLE `disease`
  MODIFY `DiseaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `group`
--
ALTER TABLE `group`
  MODIFY `GroupID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `disease`
--
ALTER TABLE `disease`
  ADD CONSTRAINT `CK_Disease_Group` FOREIGN KEY (`GroupID`) REFERENCES `group` (`GroupID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `disease_user`
--
ALTER TABLE `disease_user`
  ADD CONSTRAINT `CK_DiseaseUser_Disease` FOREIGN KEY (`DiseaseID`) REFERENCES `disease` (`DiseaseID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `CK_DiseaseUser_User` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `group`
--
ALTER TABLE `group`
  ADD CONSTRAINT `CK_Group_Chapter` FOREIGN KEY (`ChapterID`) REFERENCES `chapter` (`ChapterID`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
