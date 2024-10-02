import { FaPlus } from 'react-icons/fa';
import CanvasCard from './CanvasCard';
function LeanCanvas() {
  return (
    <div className="border-4 border-black">
      <div className="grid grid-cols-5">
        <CanvasCard title={'문제'} />
        <CanvasCard title={'문제'} />
        <CanvasCard title={'문제'} />
        <CanvasCard title={'문제'} />
        <CanvasCard title={'문제'} />
        <CanvasCard title={'기존 대안'} isSubTitle={true} />

        <CanvasCard title={'8. 핵심지표'} />
        <CanvasCard title={'상위 개념'} isSubTitle={true} />
        <CanvasCard title={'9. 고객 경로'} />
        <CanvasCard title={'얼리 어답터'} isSubTitle={true} />
      </div>
      <div className="grid grid-cols-2">
        <CanvasCard title={'7. 비용 구조'} />

        <CanvasCard title={'6. 수익 흐름'} />
      </div>
    </div>
  );
}

export default LeanCanvas;
