var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList'); // id = "cmtList"
var cmtModModalElem = document.querySelector('#modal');

function regCmt() {
	var cmtVal = cmtFrmElem.cmt.value;
	
	var param = {
		iboard : cmtListElem.dataset.iboard,
		cmt : cmtVal
	};
	regAgax(param);
	
}

// 서버에 등록
function regAgax(param) {
	const init = {
		method : 'POST',
		body : new URLSearchParams(param)
	};
	
	fetch('cmtInsSel', init)
	.then(function(res) {
		return res.json();
	})
	.then(function(myJson) { // myJson = res.json();
		console.log(myJson);
		
		switch(myJson.result) {
			case 0:
				alert('등록 실패!');
				break;
			case 1:
				cmtFrmElem.cmt.value = ''; // 수정 후 초기화
				
				getListAjax();
			break;
		}
	});
}

// 서버에게 댓글 리스트 자료 달라고 요청하는 함수
// iboard = boardDetail.jsp -> iboard

function getListAjax() {
	var iboard = cmtListElem.dataset.iboard;
	
	fetch('cmtInsSel?iboard=' + iboard)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson) {
		console.log(myJson);

		makeCmtElemList(myJson); // makeCmtElemList 함수로 보냄
	})
}

// append = 뒤로 연결, prepend = 앞으로 연결 / 서버에서 데이터를 넘길 때 동적으로 처리하기 위해 js에서 태그를 사용
function makeCmtElemList(data) {	// boardDetail.jsp 의 내용들
	
	cmtListElem.innerHTML = ''; // 안쪽에 있는 내용들을 전부 지워버림
	var tableElem = document.createElement('table'); // 가상의 테이블
	var trElemTitle = document.createElement('tr');	// 가상의 tr
	var thElemCtnt = document.createElement('th');
	var thElemWriter = document.createElement('th');
	var thElemRegdate = document.createElement('th');
	var thElemBigo = document.createElement('th');
	
	thElemCtnt.innerText = '내용';
	thElemWriter.innerText = '작성자';
	thElemRegdate.innerText = '작성일';
	thElemBigo.innerText = '비고';
	
	trElemTitle.append(thElemCtnt);
	trElemTitle.append(thElemWriter);
	trElemTitle.append(thElemRegdate);
	trElemTitle.append(thElemBigo);
	
	tableElem.append(trElemTitle);
	cmtListElem.append(tableElem);
	
	var loginUserPk = cmtListElem.dataset.login_user_pk;	// 로그인 유저의 Pk 값
	
	data.forEach(function(item) {
		var trElemCtnt = document.createElement('tr');
		var tdElem1 = document.createElement('td');
		var tdElem2 = document.createElement('td');
		var tdElem3 = document.createElement('td');
		var tdElem4 = document.createElement('td');
		
		tdElem1.append(item.cmt);
		tdElem2.append(item.writerNm);
		tdElem3.append(item.regdate);
		
		if(parseInt(loginUserPk) === item.iuser) { // boardDetail 의 login_user_pk 값을 받은 댓글 버튼
			console.log(item.iuser);
			var delBtn = document.createElement('button');
			var modBtn = document.createElement('button');
			
			// 삭제 버튼 클릭시
			delBtn.addEventListener('click', function() {
				if(confirm('삭제하시겠습니까?')) {	// confirm = boolean (참, 거짓)
					delAjax(item.icmt); // icmt값 불러오기
				}					
			});
			
			// 수정 버튼 클릭시
			modBtn.addEventListener('click', function() {
				// 댓글 수정 모달창 띄우기
				openModModal(item);
			});
			
			delBtn.innerText = '삭제';
			modBtn.innerText = '수정';
			
			tdElem4.append(delBtn);
			tdElem4.append(modBtn);
		}
		
		trElemCtnt.append(tdElem1);
		trElemCtnt.append(tdElem2);
		trElemCtnt.append(tdElem3);
		trElemCtnt.append(tdElem4);
		
		tableElem.append(trElemCtnt);
	});
}

function delAjax(icmt) {
	fetch('cmtDelUpd?icmt=' + icmt)
	.then(function(res){
		return res.json();
	})
	.then(function(data){ // "{result:0}"
		console.log(data);
		
		switch(data.result) {
			case 0:
				alert("댓글 삭제를 실패하였습니다.");
			break;
			case 1:
				getListAjax();
			break;
		}
	});
}

function modAjax() {
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	var param = {
		icmt: cmtModFrmElem.icmt.value,
		cmt: cmtModFrmElem.cmt.value
	}
	
	const init = {
		method : 'POST',
		body : new URLSearchParams(param)
	};
	
	fetch('cmtDelUpd', init)
	.then(function(res) {
		return res.json();
	})
	.then(function(myJson) {
		
		switch(myJson) {
		case 0:
			alert ('수정 실패!');
			break;
		case 1:
			getListAjax();		// 리스트를 보여주는 역할
			
			closeModModal();	// 댓글 수정 모달 닫기
			break;
		}
	});

}

function openModModal({icmt, cmt}) { // {icmt, cmt} 값을 받아온다.
	console.log('icmt : ' + icmt);
	console.log('cmt : ' + cmt);
	cmtModModalElem.className = '';
	
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	cmtModFrmElem.icmt.value = icmt;
	cmtModFrmElem.cmt.value = cmt;
}

function closeModModal() {
	cmtModModalElem.className = 'displayNone';
}

getListAjax();	// 이 파일이 임포트되면 함수 1회 호출! (리스트에 뿌려주는 역할)

// 디버깅 = F + 12 -> source -> 왼쪽 클릭 (파란색 화살표) = break 