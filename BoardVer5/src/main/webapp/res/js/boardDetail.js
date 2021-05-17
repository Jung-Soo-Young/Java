function delCmt(iboard, icmt) {
	console.log(`iboard: ${iboard}, icmt: ${icmt}`);	// ('iboard : %d, icmt: %d', iboard, icmt)
	
	if(confirm('삭제하시겠습니까?')){		//삭제 확인 메시지
		location.href = `/board/cmt?icmt=${icmt}&iboard=${iboard}`;
	}
}